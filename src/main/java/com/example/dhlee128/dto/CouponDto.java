package com.example.dhlee128.dto;

import lombok.Data;

@Data
public class CouponDto {

    private String couponNo;

    private String userId;

    private String phoneNo;

    private String security_key;

    private String goods_price;
}
