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
	
	private int mbNo;	// ȸ����ȣ
	private String id;	// ���̵�
	private String password;	// ��й�ȣ
	private String passwordConfirm;	// ��й�ȣȮ��
	private String name;	// �̸�
	private Date careerStartDate;	// ��½�������
	private String mbClsfc;	// ȸ������
	private Date registrationDttm;	// �������
	private Date withdrawalDttm;	// Ż������
	private Date frstRegiDttm;	// ���ʵ������
	private Date lastChngDttm;	// ��������������
	
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
