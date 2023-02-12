package com.vo.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vo.application.common.dto.ApiResponse;
import com.vo.application.data.dto.MemberDto;
import com.vo.application.service.MemberService;

@RestController
@RequestMapping("/api/vo/user")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	/**
	 * 회원가입
	 */
	@PostMapping("/registerUser")
	public ApiResponse<?> registerUser(@RequestBody MemberDto req) throws Exception {
		
		memberService.registerMember(req);
		
		return ApiResponse.success(null);
	}
	
	/**
	 * 로그인
	 */
	@PostMapping("/login")
	public ApiResponse<?> login(@RequestBody MemberDto req) throws Exception {
		
		memberService.login(req);
		
		return ApiResponse.success(null);
	}
	
	/**
	 * 회원 정보 조회
	 */
	@GetMapping("/getMember/{mbNo}")
	public ApiResponse<?> getMember(@PathVariable(required = true) Integer mbNo) throws Exception {
		
		return ApiResponse.success(memberService.getMember(mbNo));
	}
}