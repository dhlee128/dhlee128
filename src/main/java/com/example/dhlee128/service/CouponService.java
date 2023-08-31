package com.example.dhlee128.service;

import com.example.dhlee128.dto.CouponDto;
import com.example.dhlee128.dto.CouponResVo;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public abstract class CouponService {

    public abstract CouponResVo getSmileCoupon(CouponDto couponDto);

    public abstract CouponResVo changeCoupon(CouponDto couponDto);

    public abstract CouponResVo cancelCoupon(CouponDto couponDto);

    public String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar cal = Calendar.getInstance();

        return sdf.format(cal.getTime());
    }
}
