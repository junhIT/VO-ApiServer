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

	@Override
	public MemberEntity registerMember(MemberEntity memberEntity) {
		memberEntity.setRegistrationDttm(new Date());
		memberEntity.setFrstRegiDttm(new Date());
		memberEntity.setMbClsfc("1");	// Á¤»ó
		
		memberRepository.save(memberEntity);
		return memberEntity;
	}

	@Override
	public MemberEntity getMember(Integer mbNo) {
		return memberRepository.getReferenceById(mbNo);
	}

	@Override
	public MemberEntity getMemberById(String id) {
		return memberRepository.findById(id);
	}
}