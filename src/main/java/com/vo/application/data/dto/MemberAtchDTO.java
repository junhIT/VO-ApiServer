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
	private int mbAtchNo;	// ȸ��÷�γ�����ȣ
	private MemberDTO member;	// ȸ�����̺�
	private String actlFileNm;	// �������ϸ�
	private String fileNm;	// ���ϸ�
	private String fileUrl;	// ������ġ
	private String registrationDate;	// �������
	private String useYn;	// ��뿩��
	
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
