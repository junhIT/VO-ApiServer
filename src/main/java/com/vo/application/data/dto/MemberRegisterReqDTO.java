package com.vo.application.data.dto;

import com.vo.application.data.entity.MemberEntity;

import lombok.Getter;

@Getter
public class MemberRegisterReqDTO {

	private String id;	// ���̵�
	private String password;	// ��й�ȣ
	private String passwordConfirm;	// ��й�ȣȮ��
	private String name;	// �̸�
	private String careerStartDate;	// ��½�������
	private String mbClsfc;	// ȸ������
	
	public MemberEntity toEntity() {
		return MemberEntity.builder()
				.id(id)
				.password(password)
				.name(name)
				.careerStartDate(careerStartDate)
				.mbClsfc(mbClsfc)
				.build();
	}

}
