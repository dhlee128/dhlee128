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
    private String order_balance; //잔액, 교환권일 경우 상품가격
    private String use_balance; //사용한 금액
    private String security_key; //거래키
    private String exedate; //요청일시
    private String exchange_num; //승인번호

    private String site_user_id; //사용자 아이디

    private String barcode_num; //상품권번호

    private String isCancel; //취소가능여부

    public SmileCoupon toCouponEntity() {
        SmileCoupon coupon = new SmileCoupon();

        coupon.setBarcodeNum(this.barcode_num);
        coupon.setGoodsPrice(this.goods_price);
        coupon.setExchangeNum(this.exchange_num);
        coupon.setSecurityKey(this.security_key);
        coupon.setExedate(this.exedate);
        coupon.setUserId(this.site_user_id);

        return coupon;
    }

    public CouponHis toHisEntity() {

        CouponHis couponHis = new CouponHis();

        couponHis.setBarcodeNum(this.barcode_num);
        couponHis.setGoodsPrice(this.goods_price);
        couponHis.setOrderBalance(this.order_balance);
        couponHis.setUseBalance(this.use_balance);
        couponHis.setExchangeNum(this.exchange_num);
        couponHis.setSecurityKey(this.security_key);
        couponHis.setExedate(this.exedate);
        couponHis.setUserId(this.site_user_id);

        return couponHis;
    }

}
