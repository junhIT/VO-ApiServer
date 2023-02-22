package com.vo.application.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vo.application.data.dao.PostDAO;
import com.vo.application.data.dto.PostSaveReqDTO;
import com.vo.application.data.entity.MemberEntity;
import com.vo.application.service.PostService;

@Service
public class PostServiceImpl implements PostService{
	
	@Autowired
	private PostDAO postDao;

	/**
	 * 게시글 등록 
	 */
	public void registerPost(PostSaveReqDTO postDto) {
		
		// TODO 회원 정보 Session에서 불러오기
		postDto.setMemberEntity(MemberEntity.builder().mbNo(3).build());
		
		postDao.savePost(postDto.toEntity());
	}
}
