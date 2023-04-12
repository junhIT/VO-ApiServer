package com.vo.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	 * �Խñ� ���
	 */
	@PostMapping("/post")
	public ApiResponse<?> savePost(@RequestBody PostSaveReqDTO req) throws Exception {
		
		return ApiResponse.success(postService.registerPost(req));
	}

	/**
	 * �Խñ� ��� ��ȸ
	 */
	@GetMapping("/post")
	public ApiResponse<?> getListPost() {
		
		return ApiResponse.success(postService.getPostList());
	}
	/**
	 * �Խñ� �� ��ȸ
	 */
	@GetMapping("/post/{postNo}")
	public ApiResponse<?> getPost(@PathVariable(required = true) int postNo) {
		
		return ApiResponse.success(postService.getPost(postNo));
	}
	
	
}
