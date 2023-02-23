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
	 * ȸ������ 
	 */
	public void registerMember(MemberRegisterReqDTO req) throws Exception {
		
		if( !req.getPassword().equals(req.getPasswordConfirm()) ) {
			throw new Exception("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		}
		
		if( memberDao.getMemberById(req.getId()) != null ) {
			throw new Exception("�̹� �����ϴ� ID�Դϴ�.");
		}
		
		memberDao.registerMember(req);
	}

	/**
	 * ȸ�� �α��� 
	 */
	public void login(MemberDTO req) throws Exception {
		
		log.debug("login Start :: {}", req);
		
		MemberDTO memberDto = memberDao.getMemberById(req.getId());

		// ��ȸ�� �����Ͱ� ���� ���
		if(memberDto == null) {
			throw new Exception("ȸ�� ������ �����ϴ�.");
		}

		// ��й�ȣ�� ��ġ���� ���� ���
		if( !memberDto.getPassword().equals(req.getPassword()) ) {
			throw new Exception("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		}
	}
	
	/**
	 * ȸ�� ���� ��ȸ
	 */
	public MemberDTO getMember(Integer mbNo) throws Exception {

		return memberDao.getMember(mbNo);
	}
}