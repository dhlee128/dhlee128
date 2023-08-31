package com.example.dhlee128.repository;

import com.example.dhlee128.entity.SmileCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SmileCouponRepository extends JpaRepository<SmileCoupon, Long> {
    SmileCoupon findByUserIdAndBarcodeNum(String userId, String barcodeNum);
}
