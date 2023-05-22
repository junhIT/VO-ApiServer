package com.vo.application.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.vo.application.data.dto.CommentSaveReqDTO;

public interface CommentService {
	
	public void saveComment(CommentSaveReqDTO req, List<MultipartFile> files) throws Exception;

}
