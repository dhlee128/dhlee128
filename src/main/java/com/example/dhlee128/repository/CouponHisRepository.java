package com.example.dhlee128.repository;

import com.example.dhlee128.entity.CouponHis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CouponHisRepository extends JpaRepository<CouponHis, Long> {

}
