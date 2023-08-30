package com.example.dhlee128.dto;

import com.example.dhlee128.entity.CouponHis;
import com.example.dhlee128.entity.SmileCoupon;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CouponResVo {
    private String result_code;
    private String result_msg;

    private String goods_price; //상품가격
    private String security_key; //거래키
    private String exedate; //요청일시
    private String exchange_num; //승인번호

    private String site_user_id; //사용자 아이디

    private String barcode_num; //상품권번호

    public SmileCoupon toCouponEntity() {
        SmileCoupon coupon = new SmileCoupon();

        coupon.setUserId(this.site_user_id);
        coupon.setBarcodeNum(this.barcode_num);
        coupon.setGoodsPrice(this.goods_price);
        coupon.setSecurityKey(this.security_key);
        coupon.setExedate(this.exedate);

        return coupon;
    }

    public CouponHis toHisEntity() {

        CouponHis couponHis = new CouponHis();

        couponHis.setUserId(this.site_user_id);
        couponHis.setBarcodeNum(this.barcode_num);
        couponHis.setGoodsPrice(this.goods_price);
        couponHis.setSecurityKey(this.security_key);
        couponHis.setExedate(this.exedate);

        return couponHis;
    }

}
