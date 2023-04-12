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
	 * 회원첨부내역 저장
	 */
	@Override
	public MemberAtchDTO saveMemberAtch(MemberAtchDTO req) {
		
		req.setUseYn("Y");	// 사용여부 : Y
		// TODO : DATEUTIL DATETIMEFORMAT 생성 필요
		req.setRegistrationDate("YYYYMMDD");	// 등록일자
		
		memberAtchRepository.save(req.toEntity());
		
		return req;
	}

}
