package com.vo.application.data.dto;

import java.util.Date;

import com.vo.application.data.entity.MemberAtchEntity;
import com.vo.application.data.entity.MemberEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MemberAtchDTO {
	private int mbAtchNo;	// 회원첨부내역번호
	private MemberDTO member;	// 회원테이블
	private String actlFileNm;	// 실제파일명
	private String fileNm;	// 파일명
	private String fileUrl;	// 파일위치
	private String registrationDate;	// 등록일자
	private String useYn;	// 사용여부
	
	public MemberAtchEntity toEntity() {
		return MemberAtchEntity.builder()
								.mbAtchNo(mbAtchNo)
								.member(MemberEntity.builder().mbNo(member.getMbNo()).build())
								.actlFileNm(actlFileNm)
								.fileNm(fileNm)
								.fileUrl(fileUrl)
								.registrationDate(registrationDate)
								.useYn(useYn)
								.build();
	}
}
