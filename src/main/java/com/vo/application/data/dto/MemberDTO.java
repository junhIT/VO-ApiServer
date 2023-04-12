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
public class MemberDTO {
	
	private int mbNo;	// 회원번호
	private String id;	// 아이디
	private String password;	// 비밀번호
	private String name;	// 이름
	private String careerStartDate;	// 경력시작일자
	private String mbClsfc;	// 회원구분
	private Date registrationDttm;	// 등록일자
	private Date withdrawalDttm;	// 탈퇴일자
	
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
