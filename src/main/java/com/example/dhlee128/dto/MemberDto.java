package com.example.dhlee128.dto;

import lombok.Data;

@Data
public class MemberDto {
    private String name;

    private String userId;

    private String userPwd;

    private String phone;

    private String userPwdBefore;

    private String salt;

}
