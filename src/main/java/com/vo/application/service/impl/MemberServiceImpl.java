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
	 */
	public MemberDto registerMember(MemberDto req) {
		
		log.debug("registerMember Start :: {}", req.toString());
		
		MemberEntity resMember = memberDao.registerMember(req.toEntity());
		
		log.debug("registerMember End :: {}", resMember.toString());
		
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
		
		MemberEntity entityRes = memberDao.getMember(req.getId());

		// ��ȸ�� �����Ͱ� ���� ���
		if(entityRes == null) {
			throw new Exception("ȸ�� ������ �����ϴ�.");
		}
		
		return req;
	}
}