package com.vo.application.data.reprository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vo.application.data.entity.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Integer>{
	
}
