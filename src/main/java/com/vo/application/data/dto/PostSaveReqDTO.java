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

	private int postNo;	// 게시글번호
	private MemberEntity memberEntity;
	private String title;	// 제목
	private String content;	// 내용
	private String registrationDate;	// 등록일자
	private String closingDate;	// 마감일자
	private int	price;	//단가
	private String recordingPlace;	// 녹음장소
	private String useYn;	// 사용여부
	private Date frstRegiDttm;	// 최초등록일시
	private Date lastChngDttm;	// 마지막수정일시
	
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
