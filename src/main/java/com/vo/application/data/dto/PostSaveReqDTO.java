package com.vo.application.data.dto;

import java.util.Date;

import com.vo.application.common.util.DateUtil;
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

	private int postNo;	// 게시글번호
	private MemberDTO memberDTO;
	private String title;	// 제목
	private String content;	// 내용
	private String registrationDate;	// 등록일자
	private String closingDate;	// 마감일자
	private int	price;	//단가
	private String recordingPlace;	// 녹음장소
	private String recordingType;	// 녹음타입
	private String view;	// 조회수
	private Date frstRegiDttm;	// 최초등록일시
	private Date lastChngDttm;	// 마지막수정일시

	// TODO Session 이전 mbNo 받아서 처리하도록.
	private Integer mbNo;	// 회원번호
	
	public PostEntity toEntity() {
		return PostEntity.builder()
					.postNo(postNo)
					.member(MemberEntity.builder().mbNo(mbNo).build())
					.title(title)
					.content(content)
					.registrationDate(DateUtil.parseDate(registrationDate, DateUtil.DATE_FORMAT_yyyyMMdd))
					.closingDate(DateUtil.parseDate(closingDate, DateUtil.DATE_FORMAT_yyyyMMdd))
					.price(price)
					.recordingPlace(recordingPlace)
					.recordingType(recordingType)
					.useYn("Y")
					.build();
	}
}
