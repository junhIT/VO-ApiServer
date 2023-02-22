package com.vo.application.data.dto;

import com.vo.application.data.entity.MemberEntity;

import lombok.Getter;

@Getter
public class MemberRegisterReqDTO {

	private String id;	// 아이디
	private String password;	// 비밀번호
	private String passwordConfirm;	// 비밀번호확인
	private String name;	// 이름
	private String careerStartDate;	// 경력시작일자
	private String mbClsfc;	// 회원구분
	
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
