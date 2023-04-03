package com.vo.application.data.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "MB_PROF_TB")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MemberProfileEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mbProfAtchNo;	// ȸ�������ʹ�ȣ
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "mbNo")		// ȸ����ȣ(���ۼ���)
	private MemberEntity member;	// ȸ�����̺�
	
	private String fileNm;	// ���ϸ�
	private String fileUrl;	// ������ġ
	private Date registrationDate;	// �������
	private String useYn;	// ��뿩��
	private Date frstRegiDttm;	// ���ʵ���Ͻ�
	private Date lastChngDttm;	// �����������Ͻ�
}
