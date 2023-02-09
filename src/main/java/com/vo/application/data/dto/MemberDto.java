package com.vo.application.data.dto;

import java.util.Date;

import com.vo.application.data.entity.MemberEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class MemberDto {
	
	private int mbNo;	// 회원번호
	private String id;	// 아이디
	private String password;	// 비밀번호
	private String passwordConfirm;	// 비밀번호확인
	private String name;	// 이름
	private Date careerStartDate;	// 경력시작일자
	private String mbClsfc;	// 회원구분
	private Date registrationDttm;	// 등록일자
	private Date withdrawalDttm;	// 탈퇴일자
	private Date frstRegiDttm;	// 최초등록일자
	private Date lastChngDttm;	// 마지막수정일자
	
	public MemberEntity toEntity() {
		return MemberEntity.builder()
				.mbNo(mbNo)
				.id(id)
				.password(password)
				.name(name)
				.careerStartDate(careerStartDate)
				.mbClsfc(mbClsfc)
				.build();
	}

}
