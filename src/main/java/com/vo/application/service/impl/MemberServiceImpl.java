package com.vo.application.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vo.application.data.dao.MemberDAO;
import com.vo.application.data.dto.MemberDto;
import com.vo.application.data.dto.RegisterDto;
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
	public void registerMember(RegisterDto req) throws Exception {
		
		if( !req.getPassword().equals(req.getPasswordConfirm()) ) {
			throw new Exception("비밀번호가 일치하지 않습니다.");
		}
		
		if( memberDao.getMemberById(req.getId()) != null ) {
			throw new Exception("이미 존재하는 ID입니다.");
		}
		
		memberDao.registerMember(req.toEntity());
	}

	/**
	 * 회원 로그인 
	 */
	public void login(MemberDto req) throws Exception {
		
		log.debug("login Start :: {}", req);
		
		MemberEntity entityRes = memberDao.getMemberById(req.getId());

		// 조회된 데이터가 없을 경우
		if(entityRes == null) {
			throw new Exception("회원 정보가 없습니다.");
		}

		// 비밀번호가 일치하지 않을 경우
		if( !entityRes.getPassword().equals(req.getPassword()) ) {
			throw new Exception("비밀번호가 일치하지 않습니다.");
		}
	}
	
	/**
	 * 회원 정보 조회
	 */
	public MemberDto getMember(Integer mbNo) throws Exception {

		MemberEntity memberRes = memberDao.getMember(mbNo);
		
		return MemberDto.builder()
				.mbNo(memberRes.getMbNo())
				.id(memberRes.getId())
				.name(memberRes.getName())
				.mbClsfc(memberRes.getMbClsfc())
				.registrationDttm(memberRes.getRegistrationDttm())
				.password(memberRes.getPassword())
				.careerStartDate(memberRes.getCareerStartDate())
				.build();
	}
}