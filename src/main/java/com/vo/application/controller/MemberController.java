package com.vo.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vo.application.common.dto.ApiResponse;
import com.vo.application.data.dto.MemberDTO;
import com.vo.application.data.dto.MemberRegisterReqDTO;
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
	public ApiResponse<?> registerMember(@RequestBody MemberRegisterReqDTO req) throws Exception {
		try {
			memberService.registerMember(req);
		} catch(Exception e) {
			return ApiResponse.fail(e.getMessage());
		}
		
		return ApiResponse.success(null);
	}
	
	/**
	 * �α���
	 */
	@PostMapping("/login")
	public ApiResponse<?> login(@RequestBody MemberDTO req) throws Exception {
		try {
			memberService.login(req);
		} catch(Exception e) {
			return ApiResponse.fail(e.getMessage());
		}

		return ApiResponse.success(null);
	}
	
	/**
	 * ȸ�� ���� ��ȸ
	 */
	@GetMapping("/member/{mbNo}")
	public ApiResponse<?> getMember(@PathVariable(required = true) Integer mbNo) throws Exception {
		return ApiResponse.success(memberService.getMember(mbNo));
	}
	
	/**
	 * ȸ�� ���� ����
	 */
	@PutMapping("/member")
	public ApiResponse<?> updateMember(@RequestBody MemberDTO req) throws Exception {
		return ApiResponse.success(memberService.updateMember(req));
	}
}