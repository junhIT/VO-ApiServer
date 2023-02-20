package com.vo.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vo.application.common.dto.ApiResponse;
import com.vo.application.data.dto.MemberDto;
import com.vo.application.data.dto.RegisterDto;
import com.vo.application.service.MemberService;

@RestController
@RequestMapping("/api/vo")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	/**
	 * ȸ������
	 */
	@PostMapping("/register")
	public ApiResponse<?> registerMember(@RequestBody RegisterDto req) throws Exception {
		
		memberService.registerMember(req);
		
		return ApiResponse.success(null);
	}
	
	/**
	 * �α���
	 */
	@PostMapping("/login")
	public ApiResponse<?> login(@RequestBody MemberDto req) throws Exception {
		
		memberService.login(req);
		
		return ApiResponse.success(null);
	}
	
	/**
	 * ȸ�� ���� ��ȸ
	 */
	@GetMapping("/member/{mbNo}")
	public ApiResponse<?> getMember(@PathVariable(required = true) Integer mbNo) throws Exception {
		
		return ApiResponse.success(memberService.getMember(mbNo));
	}
}