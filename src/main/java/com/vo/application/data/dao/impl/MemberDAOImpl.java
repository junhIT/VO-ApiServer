package com.vo.application.data.dao.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vo.application.data.dao.MemberDAO;
import com.vo.application.data.dto.MemberDTO;
import com.vo.application.data.dto.MemberRegisterReqDTO;
import com.vo.application.data.entity.MemberEntity;
import com.vo.application.data.reprository.MemberRepository;

@Service
public class MemberDAOImpl implements MemberDAO{
	
	@Autowired
	MemberRepository memberRepository;

	/**
	 * ȸ�� ���
	 */
	@Override
	public void registerMember(MemberRegisterReqDTO req) {
		
		MemberEntity entityReq = req.toEntity();
		
		entityReq.setRegistrationDttm(new Date());
		entityReq.setFrstRegiDttm(new Date());
		entityReq.setMbClsfc("1");	// ����
		
		memberRepository.save(entityReq);
	}

	/**
	 * ȸ����ȣ�� ȸ�� ���� ��ȸ
	 */
	@Override
	public MemberDTO getMember(Integer mbNo) {
		MemberEntity entityRes = memberRepository.getReferenceById(mbNo);
		
		return MemberDTO.builder()
						.id(entityRes.getId())
						.name(entityRes.getName())
						.careerStartDate(entityRes.getCareerStartDate())
						.mbClsfc(entityRes.getMbClsfc())
						.registrationDttm(entityRes.getRegistrationDttm())
						.withdrawalDttm(entityRes.getWithdrawalDttm())
						.build();
	}

	/**
	 * ID�� ȸ�� ���� ��ȸ 
	 */
	@Override
	public MemberDTO getMemberById(String id) {
		MemberEntity entityRes = memberRepository.findById(id);
		
		return MemberDTO.builder()
				.mbNo(entityRes.getMbNo())
				.id(entityRes.getId())
				.password(entityRes.getPassword())
				.name(entityRes.getName())
				.careerStartDate(entityRes.getCareerStartDate())
				.mbClsfc(entityRes.getMbClsfc())
				.registrationDttm(entityRes.getRegistrationDttm())
				.withdrawalDttm(entityRes.getWithdrawalDttm())
				.build();
	}

	/**
	 * ȸ�� ���� ������Ʈ
	 */
	@Override
	public MemberDTO updateMember(MemberDTO memberDto) {
		
		MemberEntity entityReq = memberRepository.findById(memberDto.getId());
		
		entityReq.setName(memberDto.getName());
		entityReq.setCareerStartDate(memberDto.getCareerStartDate());
		entityReq.setPassword(memberDto.getPassword());

		MemberEntity entityRes = memberRepository.save(entityReq);
		
		return MemberDTO.builder()
				.id(entityRes.getId())
				.password(entityRes.getPassword())
				.name(entityRes.getName())
				.careerStartDate(entityRes.getCareerStartDate())
				.mbClsfc(entityRes.getMbClsfc())
				.registrationDttm(entityRes.getRegistrationDttm())
				.withdrawalDttm(entityRes.getWithdrawalDttm())
				.build();	
	}
}