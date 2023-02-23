package com.vo.application.data.dao;

import java.util.List;

import com.vo.application.data.dto.PostDTO;
import com.vo.application.data.dto.PostSaveReqDTO;

public interface PostDAO {

	// 게시글 저장
	PostDTO savePost(PostSaveReqDTO postEntity);
	
	// 게시글 목록 조회
	List<PostDTO> getPostList();
	
	// 게시글 상세 조회
	PostDTO getPost(int postNo);
}
