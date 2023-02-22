package com.vo.application.data.dao.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vo.application.data.dao.PostDAO;
import com.vo.application.data.entity.PostEntity;
import com.vo.application.data.reprository.PostRepository;

@Service
public class PostDAOImpl implements PostDAO {
	
	@Autowired
	PostRepository postRepository;
	
	/**
	 * 게시글 저장
	 */
	@Override
	public void savePost(PostEntity postEntity) {
		postRepository.save(postEntity);
	}
}