package com.example.dhlee128.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CouponController {

    @GetMapping("/coupon")
    public String coupon() {
        return "coupon";
    }

    @GetMapping("/coupon2")
    public String coupon2() {
        return "coupon2";
    }

    @GetMapping("/coupon3")
    public String coupon3() {
        return "coupon3";
    }

    @GetMapping("/coupon4")
    public String coupon4() {
        return "coupon4";
    }
}
