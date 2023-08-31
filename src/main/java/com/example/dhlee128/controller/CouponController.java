package com.example.dhlee128.controller;

import com.example.dhlee128.dto.CouponDto;
import com.example.dhlee128.dto.CouponResVo;
import com.example.dhlee128.entity.Member;
import com.example.dhlee128.service.GiftCouponService;
import com.example.dhlee128.service.SmileCouponService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CouponController {
    @GetMapping("/coupon")
    public String couponPage() {
        return "coupon";
    }

    private final GiftCouponService giftCouponService;
    private final SmileCouponService SmileCouponService;

    @ResponseBody
    @GetMapping("/giftCoupon")
    public CouponResVo giftCoupon(CouponDto couponDto, HttpServletRequest request) {

        Member member = (Member)request.getSession().getAttribute("sessionMember");
        couponDto.setUserId(member.getUserId());

        return giftCouponService.getCoupon(couponDto);

    }

    @ResponseBody
    @GetMapping("/smileCoupon/{couponNo}")
    public CouponResVo getSmileCouponProc(@PathVariable String couponNo, HttpServletRequest request) {

        Member member = (Member)request.getSession().getAttribute("sessionMember");
        CouponDto couponDto = new CouponDto();
        couponDto.setCouponNo(couponNo);
        couponDto.setUserId(member.getUserId());

        return SmileCouponService.getCoupon(couponDto);

    }

    @ResponseBody
    @PostMapping("/changeSmileCoupon")
    public CouponResVo changeSmileCouponProc(@RequestBody CouponDto couponDto, HttpServletRequest request) {

        Member member = (Member)request.getSession().getAttribute("sessionMember");
        couponDto.setUserId(member.getUserId());

        return SmileCouponService.changeCoupon(couponDto);

    }

    @ResponseBody
    @PostMapping("/cancelSmileCoupon")
    public CouponResVo cancelSmileCouponProc(@RequestBody CouponDto couponDto, HttpServletRequest request) {

        Member member = (Member)request.getSession().getAttribute("sessionMember");
        couponDto.setUserId(member.getUserId());

        return SmileCouponService.cancelCoupon(couponDto);

    }
}
