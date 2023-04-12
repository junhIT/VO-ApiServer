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
	 * 게시글 등록 
	 * @throws Exception 
	 */
	public PostDTO registerPost(PostSaveReqDTO postDto) throws Exception {
		
		if(postDto.getMbNo() == null) {
			throw new Exception("MbNO가 없어요");
		}
		
		// TODO 회원 정보 Session에서 불러오기
		postDto.setMemberDTO(MemberDTO.builder().mbNo(postDto.getMbNo()).build());
		
		return postDao.savePost(postDto);
	}

	/**
	 * 게시글 목록 조회
	 */
	@Override
	public List<PostDTO> getPostList() {
		
		return postDao.getPostList();
	}

	/**
	 * 게시글 상세 조회
	 */
	@Override
	public PostDTO getPost(int postNo) {
		
		return postDao.getPost(postNo);
	}
}
