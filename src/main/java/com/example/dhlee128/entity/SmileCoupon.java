package com.example.dhlee128.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name="SMILE_COUPON")
public class SmileCoupon {

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
    private String exchangeNum;

    @NotNull
    private String exedate;

}
