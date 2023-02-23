package com.vo.application.data.dto;

import java.util.Date;

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
public class PostDTO {

	private Integer postNo;	// �Խñ۹�ȣ
	private Integer memberNo;	// ȸ����ȣ
	private String title;	// ����
	private String content;	// ����
	private String registrationDate;	// �������
	private String closingDate;	// ��������
	private Integer	price;	//�ܰ�
	private String recordingPlace;	// �������
	private String useYn;	// ��뿩��
	private Date frstRegiDttm;	// ���ʵ���Ͻ�
	private Date lastChngDttm;	// �����������Ͻ�
	
}
