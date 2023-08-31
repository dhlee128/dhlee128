package com.example.dhlee128.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name="GIFT_COUPON")
public class GiftCoupon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long seq;


}
