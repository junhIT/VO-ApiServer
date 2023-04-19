package com.vo.application.controller;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vo.application.common.dto.ApiResponse;
import com.vo.application.data.dto.MemberDTO;
import com.vo.application.data.dto.MemberRegisterReqDTO;
import com.vo.application.data.entity.MemberAtchEntity;
import com.vo.application.data.reprository.MemberAtchRepository;
import com.vo.application.service.MemberService;

@RestController
@RequestMapping("/api/vo")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MemberAtchRepository memberAtchRepository;
	
	
	/**
	 * 회원가입
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
	 * 로그인
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
	 * 회원 정보 조회
	 */
	@GetMapping("/member/{id}")
	public ApiResponse<?> getMember(@PathVariable(required = true) String id) throws Exception {
		return ApiResponse.success(memberService.getMember(id));
	}
	
	/**
	 * 회원 정보 변경
	 */
	@PutMapping("/member")
	public ApiResponse<?> updateMember(MemberDTO req, MultipartFile file) throws Exception {
		return ApiResponse.success(memberService.updateMember(req, file));
	}
	
	/**
	 * 프로필 이미지 출력
	 */
	@GetMapping("/member/profile/{mbNo}")
	public ApiResponse<?> getMemberProfileImg(@PathVariable(required = true) int mbNo) throws Exception {
		// TODO :: 프로필 이미지 Download & byteArray 출력 작성하고 추후 FileUtil에 공통 Class 생성
		MemberAtchEntity res = memberAtchRepository.findByMember_MbNo(mbNo);
		
		// 프로필 이미지가 없을 경우 정상처리
		if(res == null) {
			return ApiResponse.success(null);
		}
		
		String fileUrl = res.getFileUrl();
		String fileName = res.getFileNm();
		String fileFullUrl = fileUrl + "/" + fileName;	// TODO 서버경로
		
		// local일 경우 url
		if( StringUtils.defaultString(System.getProperty("Spring.profiles.active")).equals("local") ) {
			fileFullUrl = fileUrl + "\\" + fileName;
		}
		
		File profileImg = new File(fileFullUrl);
		
		byte[] imageByteArray = IOUtils.toByteArray(new FileInputStream(profileImg));
		
		return ApiResponse.success(imageByteArray);
	}
}