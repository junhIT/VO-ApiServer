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
	
	private int mbNo;	// ȸ����ȣ
	private String id;	// ���̵�
	private String password;	// ��й�ȣ
	private String name;	// �̸�
	private String careerStartDate;	// ��½�������
	private String mbClsfc;	// ȸ������
	private Date registrationDttm;	// �������
	private Date withdrawalDttm;	// Ż������
	
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
