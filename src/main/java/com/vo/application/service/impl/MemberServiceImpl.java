package com.vo.application.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vo.application.data.dao.MemberDAO;
import com.vo.application.data.dto.MemberDTO;
import com.vo.application.data.dto.MemberRegisterReqDTO;
import com.vo.application.data.entity.MemberEntity;
import com.vo.application.data.entity.PostEntity;
import com.vo.application.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberDAO memberDao;

	/**
	 * 회원가입 
	 */
	public void registerMember(MemberRegisterReqDTO req) throws Exception {
		
		if( !req.getPassword().equals(req.getPasswordConfirm()) ) {
			throw new Exception("비밀번호가 일치하지 않습니다.");
		}
		
		if( memberDao.getMemberById(req.getId()) != null ) {
			throw new Exception("이미 존재하는 ID입니다.");
		}
		
		memberDao.registerMember(req);
	}

	/**
	 * 회원 로그인 
	 */
	public void login(MemberDTO req) throws Exception {
		
		log.debug("login Start :: {}", req);
		
		MemberDTO memberDto = memberDao.getMemberById(req.getId());

		// 조회된 데이터가 없을 경우
		if(memberDto == null) {
			throw new Exception("회원 정보가 없습니다.");
		}

		// 비밀번호가 일치하지 않을 경우
		if( !memberDto.getPassword().equals(req.getPassword()) ) {
			throw new Exception("비밀번호가 일치하지 않습니다.");
		}
	}
	
	/**
	 * 회원 정보 조회
	 */
	public MemberDTO getMember(Integer mbNo) throws Exception {

		return memberDao.getMember(mbNo);
	}
}