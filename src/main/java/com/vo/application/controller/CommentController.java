package com.vo.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vo.application.common.dto.ApiResponse;
import com.vo.application.data.dto.CommentSaveReqDTO;
import com.vo.application.data.dto.PostSaveReqDTO;
import com.vo.application.service.CommentService;

/**
 * 댓글 관련 Controller
 * @author JunHi
 *
 */
@RestController
@RequestMapping("/api/vo")
public class CommentController {
	
	@Autowired
	private CommentService commentService;

	/**
	 * 댓글 등록
	 */
	@PostMapping("/comment")
	public ApiResponse<?> saveComment(@ModelAttribute CommentSaveReqDTO req
									, @RequestParam(value="files") List<MultipartFile> files) throws Exception {

		commentService.saveComment(req, files);
		return ApiResponse.success(null);
	}
	
	/**
	 * 댓글 수정
	 */
	
	/**
	 * 댓글 삭제
	 */
	
}
