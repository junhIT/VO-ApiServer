package com.vo.application.data.reprository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vo.application.data.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer>{
    
//	@Query("SELECT c FROM CommentEntity c JOIN FETCH c.commentAtchList ca WHERE c.post.postNo = :postNo")
    @Query("SELECT c FROM CommentEntity c WHERE c.post.postNo = :postNo")
    List<CommentEntity> findByPostNoWithCommentAtch(@Param("postNo") int postNo);

}