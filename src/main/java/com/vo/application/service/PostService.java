package com.vo.application.service;

import java.util.List;

import com.vo.application.data.dto.PostDTO;
import com.vo.application.data.dto.PostSaveReqDTO;

public interface PostService {
	
	// 게시글 저장
	PostDTO registerPost(PostSaveReqDTO postDto) throws Exception;
	
	// 게시글 목록 조회
	List<PostDTO> getPostList();

	// 게시글 상세 조회
	PostDTO getPost(int postNo);
}
