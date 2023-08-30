package com.example.dhlee128.entity;

import com.example.dhlee128.dto.MemberDto;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name="MEMBER")
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long seq;

    @NonNull
    private String userName;

    @NonNull
    private String userId;

    @NotNull
    private String userPwd;

    private String phone;

    @NotNull
    private String salt;

    @Builder
    public Member(String userName, String userId, String userPwd, String phone, String salt) {
        this.userName = userName;
        this.userId = userId;
        this.userPwd = userPwd;
        this.phone = phone;
        this.salt = salt;
    }

    public void update(MemberDto dto) {
        this.salt = dto.getSalt();
        this.userPwd = dto.getUserPwd();
        this.phone = dto.getPhone();
    }
}
