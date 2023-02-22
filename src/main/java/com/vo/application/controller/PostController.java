package com.vo.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vo.application.common.dto.ApiResponse;
import com.vo.application.data.dto.PostSaveReqDTO;
import com.vo.application.service.PostService;

@RestController
@RequestMapping("/api/vo")
public class PostController {
	
	@Autowired
	PostService postService;
	
	/**
	 * 게시글 등록
	 */
	@PostMapping("/post")
	public Object savePost(@RequestBody PostSaveReqDTO req) {
		
		postService.registerPost(req);
		
		return ApiResponse.success(null);
	}
}
