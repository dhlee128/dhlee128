package com.example.dhlee128.repository;

import com.example.dhlee128.entity.GiftCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiftCouponRepository extends JpaRepository<GiftCoupon, Long> {

}
