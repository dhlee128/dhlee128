package com.example.dhlee128.entity;

import com.sun.istack.NotNull;
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
    private String use_balance;

    @NotNull
    private String orderBalance;

    @NotNull
    private String exedate;

    @NotNull
    private String couponType;

    @NotNull
    private String procType;

    @NotNull
    private String securityKey;

    @NotNull
    private String exchangeNum;

}
