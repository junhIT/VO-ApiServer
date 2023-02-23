package com.vo.application.data.dao;

import java.util.List;

import com.vo.application.data.dto.PostDTO;
import com.vo.application.data.dto.PostSaveReqDTO;

public interface PostDAO {

	// �Խñ� ����
	PostDTO savePost(PostSaveReqDTO postEntity);
	
	// �Խñ� ��� ��ȸ
	List<PostDTO> getPostList();
	
	// �Խñ� �� ��ȸ
	PostDTO getPost(int postNo);
}
