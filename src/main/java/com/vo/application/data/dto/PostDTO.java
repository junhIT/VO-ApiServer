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

	private Integer postNo;	// 게시글번호
	private Integer memberNo;	// 회원번호
	private String title;	// 제목
	private String content;	// 내용
	private String registrationDate;	// 등록일자
	private String closingDate;	// 마감일자
	private Integer	price;	//단가
	private String recordingPlace;	// 녹음장소
	private String useYn;	// 사용여부
	private Date frstRegiDttm;	// 최초등록일시
	private Date lastChngDttm;	// 마지막수정일시
	
}
