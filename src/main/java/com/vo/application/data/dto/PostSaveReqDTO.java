package com.vo.application.data.dto;

import java.util.Date;

import com.vo.application.data.entity.MemberEntity;
import com.vo.application.data.entity.PostEntity;

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
public class PostSaveReqDTO {

	private int postNo;	// �Խñ۹�ȣ
	private MemberEntity memberEntity;
	private String title;	// ����
	private String content;	// ����
	private String registrationDate;	// �������
	private String closingDate;	// ��������
	private int	price;	//�ܰ�
	private String recordingPlace;	// �������
	private String useYn;	// ��뿩��
	private Date frstRegiDttm;	// ���ʵ���Ͻ�
	private Date lastChngDttm;	// �����������Ͻ�
	
	public PostEntity toEntity() {
		return PostEntity.builder()
					.postNo(postNo)
					.member(memberEntity)
					.title(title)
					.content(content)
					.registrationDate(registrationDate)
					.closingDate(closingDate)
					.price(price)
					.recordingPlace(recordingPlace)
					.useYn(useYn)
					.build();
	}
}
