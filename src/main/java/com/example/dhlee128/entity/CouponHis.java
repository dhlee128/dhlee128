package com.example.dhlee128.entity;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name="COUPON_HIS")
public class CouponHis {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long seq;

    @NonNull
    private String userId;

    @NotNull
    private String barcodeNum;

    @NotNull
    private String goodsPrice;

    @NotNull
    private String securityKey;

    @NotNull
    private String exedate;

    private String exchangeNum;

    @NotNull
    private String couponType;

    @NotNull
    private String procType;

    @Builder
    public CouponHis(String userId, String goodsPrice, String securityKey, String exedate, String couponType, String procType, String barcodeNum) {
        this.userId = userId;
        this.goodsPrice = goodsPrice;
        this.securityKey = securityKey;
        this.exedate = exedate;
        this.couponType = couponType;
        this.procType = procType;
        this.barcodeNum = barcodeNum;
    }

}
