package com.vo.application.data.dao.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vo.application.data.dao.MemberAtchDAO;
import com.vo.application.data.dto.MemberAtchDTO;
import com.vo.application.data.reprository.MemberAtchRepository;

@Service
public class MemberAtchDAOImpl implements MemberAtchDAO {
	
	@Autowired
	MemberAtchRepository memberAtchRepository;

	/**
	 * ȸ��÷�γ��� ����
	 */
	@Override
	public MemberAtchDTO saveMemberAtch(MemberAtchDTO req) {
		
		req.setUseYn("Y");	// ��뿩�� : Y
		// TODO : DATEUTIL DATETIMEFORMAT ���� �ʿ�
		req.setRegistrationDate("YYYYMMDD");	// �������
		
		memberAtchRepository.save(req.toEntity());
		
		return req;
	}

}
