package com.vo.application.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vo.application.data.dao.MemberDAO;
import com.vo.application.data.dto.MemberDto;
import com.vo.application.data.entity.MemberEntity;
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
	public MemberDto registerMember(MemberDto req) {
		
		log.debug("registerMember Start :: {}", req.toString());
		
		MemberEntity resMember = memberDao.registerMember(req.toEntity());
		
		log.debug("registerMember End :: {}", resMember.toString());
		
		return req;
	}

	/**
	 * 회원 로그인 
	 */
	public MemberDto login(MemberDto req) throws Exception {
		
		log.debug("login Start :: {}", req);
		
		if( !req.getPassword().equals(req.getPasswordConfirm()) ) {
			throw new Exception("비밀번호가 일치하지 않습니다.");
		}
		
		MemberEntity entityRes = memberDao.getMember(req.getId());

		// 조회된 데이터가 없을 경우
		if(entityRes == null) {
			throw new Exception("회원 정보가 없습니다.");
		}
		
		return req;
	}
}