package com.vo.application.data.entity;

import java.util.Date;

import jakarta.persistence.Column;
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

@Entity
@Table(name = "POST_TB")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class PostEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int postNo;	// �Խñ۹�ȣ
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "mbNo")		// ȸ����ȣ(���ۼ���)
	private MemberEntity member;	// ȸ�����̺�
	
	private String title;	// ����
	private String content;	// ����
	private String registrationDate;	// �������
	private String closingDate;	// ��������
	private int	price;	//�ܰ�
	private String recordingPlace;	// �������
	private String useYn;	// ��뿩��
	
	@Column(columnDefinition = "integer default 0", nullable = false)
	private int view;	// ��ȸ��
	private Date frstRegiDttm;	// ���ʵ���Ͻ�
	private Date lastChngDttm;	// �����������Ͻ�
}
