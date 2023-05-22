package com.vo.application.data.reprository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vo.application.data.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer>{

}