package com.vo.application.service;

import java.util.List;

import com.vo.application.data.dto.PostDTO;
import com.vo.application.data.dto.PostSaveReqDTO;

public interface PostService {
	
	// �Խñ� ����
	PostDTO registerPost(PostSaveReqDTO postDto) throws Exception;
	
	// �Խñ� ��� ��ȸ
	List<PostDTO> getPostList();

	// �Խñ� �� ��ȸ
	PostDTO getPost(int postNo);
}
