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
	 * ȸ������ 
	 */
	public void registerMember(RegisterDto req) throws Exception {
		
		if( !req.getPassword().equals(req.getPasswordConfirm()) ) {
			throw new Exception("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		}
		
		if( memberDao.getMemberById(req.getId()) != null ) {
			throw new Exception("�̹� �����ϴ� ID�Դϴ�.");
		}
		
		memberDao.registerMember(req.toEntity());
	}

	/**
	 * ȸ�� �α��� 
	 */
	public void login(MemberDto req) throws Exception {
		
		log.debug("login Start :: {}", req);
		
		MemberEntity entityRes = memberDao.getMemberById(req.getId());

		// ��ȸ�� �����Ͱ� ���� ���
		if(entityRes == null) {
			throw new Exception("ȸ�� ������ �����ϴ�.");
		}

		// ��й�ȣ�� ��ġ���� ���� ���
		if( !entityRes.getPassword().equals(req.getPassword()) ) {
			throw new Exception("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		}
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
				.build();
	}
}