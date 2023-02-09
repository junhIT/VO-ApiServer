package com.vo.application.data.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MB_TB")
@Builder
public class MemberEntity {
	
	@Id
	private int mbNo;	// ȸ����ȣ
	
	@Column(unique = true)
	private String id;	// ���̵�
	private String password;	// ��й�ȣ
	private String name;	// �̸�
	private Date careerStartDate;	// ��½�������
	private String mbClsfc;	// ȸ������
	private Date registrationDttm;	// �������
	private Date withdrawalDttm;	// Ż������
	private Date frstRegiDttm;	// ���ʵ������
	private Date lastChngDttm;	// ��������������
	
}
