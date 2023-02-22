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
	 * �Խñ� ��� 
	 */
	public void registerPost(PostSaveReqDTO postDto) {
		
		// TODO ȸ�� ���� Session���� �ҷ�����
		postDto.setMemberEntity(MemberEntity.builder().mbNo(3).build());
		
		postDao.savePost(postDto.toEntity());
	}
}
