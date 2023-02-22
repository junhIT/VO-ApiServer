package com.vo.application.data.dao.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vo.application.data.dao.MemberDAO;
import com.vo.application.data.entity.MemberEntity;
import com.vo.application.data.reprository.MemberRepository;

@Service
public class MemberDAOImpl implements MemberDAO{
	
	@Autowired
	MemberRepository memberRepository;

	/**
	 * 회원 등록
	 */
	@Override
	public MemberEntity registerMember(MemberEntity memberEntity) {
		memberEntity.setRegistrationDttm(new Date());
		memberEntity.setFrstRegiDttm(new Date());
		memberEntity.setMbClsfc("1");	// 정상
		
		memberRepository.save(memberEntity);
		return memberEntity;
	}

	/**
	 * 회원번호로 회원 정보 조회
	 */
	@Override
	public MemberEntity getMember(Integer mbNo) {
		return memberRepository.getReferenceById(mbNo);
	}

	/**
	 * ID로 회원 정보 조회 
	 */
	@Override
	public MemberEntity getMemberById(String id) {
		return memberRepository.findById(id);
	}
}