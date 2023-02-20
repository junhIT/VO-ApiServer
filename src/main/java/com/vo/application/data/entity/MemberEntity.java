package com.vo.application.data.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mbNo;	// ȸ����ȣ
	
	@Column(unique = true)
	private String id;	// ���̵�
	private String password;	// ��й�ȣ
	private String name;	// �̸�
	private String careerStartDate;	// ��½�������
	private String mbClsfc;	// ȸ������
	private Date registrationDttm;	// ����Ͻ�
	private Date withdrawalDttm;	// Ż���Ͻ�
	private Date frstRegiDttm;	// ���ʵ���Ͻ�
	private Date lastChngDttm;	// �����������Ͻ�
	
}
