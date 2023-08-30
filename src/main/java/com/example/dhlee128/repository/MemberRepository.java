package com.example.dhlee128.repository;

import com.example.dhlee128.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByUserId(String UserId);

}
