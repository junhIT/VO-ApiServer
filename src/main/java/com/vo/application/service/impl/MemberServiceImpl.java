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
	 * ȸ������ 
	 * @throws Exception 
	 */
	public MemberDto registerMember(MemberDto req) throws Exception {
		
		if( !req.getPassword().equals(req.getPasswordConfirm()) ) {
			throw new Exception("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		}
		
		MemberEntity resMember = memberDao.registerMember(req.toEntity());
		
		return req;
	}

	/**
	 * ȸ�� �α��� 
	 */
	public MemberDto login(MemberDto req) throws Exception {
		
		log.debug("login Start :: {}", req);
		
		if( !req.getPassword().equals(req.getPasswordConfirm()) ) {
			throw new Exception("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		}
		
		MemberEntity entityRes = memberDao.login(req.getId());

		// ��ȸ�� �����Ͱ� ���� ���
		if(entityRes == null) {
			throw new Exception("ȸ�� ������ �����ϴ�.");
		}
		
		return req;
	}
	
	/**
	 * ȸ�� ���� ��ȸ
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
				.frstRegiDttm(memberRes.getFrstRegiDttm())
				.lastChngDttm(memberRes.getLastChngDttm())
				.build();
	}
}