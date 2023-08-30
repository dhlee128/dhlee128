package com.example.dhlee128.service;

import com.example.dhlee128.dto.LoginDto;
import com.example.dhlee128.dto.MemberDto;
import com.example.dhlee128.entity.Member;
import com.example.dhlee128.repository.MemberRepository;
import com.example.dhlee128.util.Encrypt;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    private final Encrypt encrypt;

    @Transactional
    public boolean join(MemberDto dto) {

        Member findMember = memberRepository.findByUserId(dto.getUserId());

        if(findMember!=null) return false;

        String salt = encrypt.getSalt();

        Member member = Member.builder()
                .userName(dto.getName())
                .userId(dto.getUserId())
                .userPwd(encrypt.getEncrypt(dto.getUserPwd(), salt))
                .phone(dto.getPhone())
                .salt(salt)
                .build();

        memberRepository.save(member);

        return true;
    }

    public Member login(LoginDto dto) {

        Member findMember = memberRepository.findByUserId(dto.getLoginId());

        if(findMember==null) return null;

        if(checkPwd(findMember, dto.getLoginPwd())) return findMember;

        return null;

    }

    @Transactional
    public String updateMemberInfo(MemberDto dto) {

        Member findMember = memberRepository.findByUserId(dto.getUserId());

        if(findMember==null) return "fail";

        if(!checkPwd(findMember, dto.getUserPwdBefore())) return "fail_pwd";

        String salt = encrypt.getSalt();

        dto.setSalt(salt);
        dto.setUserPwd(encrypt.getEncrypt(dto.getUserPwd(), salt));

        findMember.update(dto);

        return "ok";
    }

    private boolean checkPwd(Member inDbMember, String loginPwd) {

        String encLoginPwd = "";
        encLoginPwd = encrypt.getEncrypt(loginPwd, inDbMember.getSalt());

        return encLoginPwd.equals(inDbMember.getUserPwd());
    }
}
