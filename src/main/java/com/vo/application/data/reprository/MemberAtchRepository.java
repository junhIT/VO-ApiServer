package com.vo.application.data.reprository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vo.application.data.entity.MemberAtchEntity;

/**
 * 회원첨부내역 테이블 Repository 
 */
public interface MemberAtchRepository extends JpaRepository<MemberAtchEntity, Integer>{

}
