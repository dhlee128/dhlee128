package com.example.dhlee128.controller;

import com.example.dhlee128.dto.CouponDto;
import com.example.dhlee128.dto.CouponResVo;
import com.example.dhlee128.entity.Member;
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
    public String coupon() {
        return "coupon";
    }

    //private final GiftCouponService giftCouponService;
    private final SmileCouponService SmileCouponService;

    @ResponseBody
    @GetMapping("/giftCoupon")
    public CouponResVo giftCoupon(CouponDto couponDto, HttpServletRequest request) {

        Member member = (Member)request.getSession().getAttribute("sessionMember");
        couponDto.setUserId(member.getUserId());

        //return giftCouponService.findCouponInfo(couponDto);
        return null;
    }

    @ResponseBody
    @GetMapping("/smileCoupon/{couponNo}")
    public CouponResVo getSmileCoupon(@PathVariable String couponNo, HttpServletRequest request) {

        Member member = (Member)request.getSession().getAttribute("sessionMember");
        CouponDto couponDto = new CouponDto();
        couponDto.setCouponNo(couponNo);
        couponDto.setUserId(member.getUserId());

        return SmileCouponService.getSmileCoupon(couponDto);

    }

    @ResponseBody
    @PostMapping("/changeSmileCoupon")
    public CouponResVo changeSmileCoupon(@RequestBody CouponDto couponDto, HttpServletRequest request) {

        Member member = (Member)request.getSession().getAttribute("sessionMember");
        couponDto.setUserId(member.getUserId());

        return SmileCouponService.changeCoupon(couponDto);

    }

    @ResponseBody
    @PostMapping("/cancelSmileCoupon")
    public CouponResVo cancelSmileCoupon(@RequestBody CouponDto couponDto, HttpServletRequest request) {

        Member member = (Member)request.getSession().getAttribute("sessionMember");
        couponDto.setUserId(member.getUserId());

        return SmileCouponService.cancelCoupon(couponDto);

    }
}
