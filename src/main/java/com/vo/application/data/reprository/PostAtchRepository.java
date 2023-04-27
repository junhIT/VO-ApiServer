package com.vo.application.data.reprository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vo.application.data.entity.PostAtchEntity;

/**
 * 게시글첨부내역 테이블 Repository 
 */
public interface PostAtchRepository extends JpaRepository<PostAtchEntity, Integer>{
	
}
