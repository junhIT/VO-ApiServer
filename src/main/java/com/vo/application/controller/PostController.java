package com.vo.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	public ApiResponse<?> savePost(@ModelAttribute PostSaveReqDTO req
								   , @RequestParam(value="scripts") List<MultipartFile> scripts
								   , @RequestParam(value="images") List<MultipartFile> images) throws Exception {
		
		return ApiResponse.success(postService.registerPost(req, scripts, images));
	}

	/**
	 * 게시글 목록 조회
	 */
	@GetMapping("/post")
	public ApiResponse<?> getListPost() {
		
		return ApiResponse.success(postService.getPostList());
	}
	
	/**
	 * 게시글 상세 조회
	 */
	@GetMapping("/post/{postNo}")
	public ApiResponse<?> getPost(@PathVariable(required = true) int postNo) {
		
		return ApiResponse.success(postService.getPost(postNo));
	}
}
