package com.vo.application.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vo.application.data.dao.PostDAO;
import com.vo.application.data.dto.MemberDTO;
import com.vo.application.data.dto.PostDTO;
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
	public PostDTO registerPost(PostSaveReqDTO postDto) {
		
		// TODO ȸ�� ���� Session���� �ҷ�����
		postDto.setMemberDTO(MemberDTO.builder().mbNo(3).build());
		
		return postDao.savePost(postDto);
	}

	/**
	 * �Խñ� ��� ��ȸ
	 */
	@Override
	public List<PostDTO> getPostList() {
		
		return postDao.getPostList();
	}

	/**
	 * �Խñ� �� ��ȸ
	 */
	@Override
	public PostDTO getPost(int postNo) {
		
		return postDao.getPost(postNo);
	}
}
