package com.example.dhlee128.controller;

import com.example.dhlee128.dto.LoginDto;
import com.example.dhlee128.dto.MemberDto;
import com.example.dhlee128.entity.Member;
import com.example.dhlee128.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/memberInfo")
    public String memberPage() {

        return "memberInfo";
    }

    @GetMapping("/join")
    public String joinPage() {
        return "join";
    }

    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if(session!=null) {
            session.invalidate();
        }

        return "redirect:/";
    }

    @ResponseBody
    @PostMapping("/login")
    public String loginProc(@ModelAttribute LoginDto dto, HttpServletRequest request) {

        Member member = memberService.login(dto);

        if(member==null) {
            return "fail";
        } else{
            HttpSession session = request.getSession(true);

            session.setAttribute("sessionMember", member);

            return "ok";
        }
    }

    @ResponseBody
    @PostMapping("/join")
    public String joinProc(MemberDto dto) {

        if(memberService.join(dto)) return "ok";

        return "fail";
    }

    @ResponseBody
    @PostMapping("/memberInfoEdit")
    public String memberInfoEditProc(HttpServletRequest request, MemberDto dto) {

        Member sessionMemberInfo = (Member)request.getSession().getAttribute("sessionMember");
        if(!sessionMemberInfo.getUserId().equals(dto.getUserId())) return "fail";

        return memberService.updateMemberInfo(dto);
    }
}
