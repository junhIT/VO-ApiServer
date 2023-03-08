package com.vo.application.data.reprository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vo.application.data.entity.PostEntity;

import jakarta.transaction.Transactional;

public interface PostRepository extends JpaRepository<PostEntity, Integer>{

	@Transactional
	@Modifying
	@Query("update PostEntity set view = view + 1 where postNo = :postNo")
	int updateView(@Param(value = "postNo") int postNo);
}
