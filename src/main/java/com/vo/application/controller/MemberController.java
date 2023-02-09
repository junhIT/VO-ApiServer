package com.vo.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<?> registerUser(@RequestBody MemberDto req) {
		
		memberService.registerMember(req);
		
		return ResponseEntity.ok("success");
	}
	
	/**
	 * 로그인
	 */
	@PostMapping("/login")
	public ResponseEntity<?> getUser(@RequestBody MemberDto req) throws Exception {
		
		memberService.login(req);
		
		return ResponseEntity.ok("success");
	}
	
}