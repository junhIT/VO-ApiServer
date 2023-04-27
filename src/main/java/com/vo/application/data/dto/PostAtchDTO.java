package com.vo.application.data.dto;

import java.util.Date;

import com.vo.application.data.entity.MemberAtchEntity;
import com.vo.application.data.entity.MemberEntity;
import com.vo.application.data.entity.PostAtchEntity;
import com.vo.application.data.entity.PostEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostAtchDTO {
	private int postAtchNo;	// 게시글첨부내역번호
	private int postNo;	// 게시글번호
	private String actlFileNm;	// 실제파일명
	private String fileDivision;	// 파일구분
	private String fileNm;	// 파일명
	private String fileUrl;	// 파일위치
	private String registrationDate;	// 등록일자
	private String atchIdx;	// 첨부순번
	private String useYn;	// 사용여부
	private Date frstRegiDttm;	// 최초등록일시
	private Date lastChngDttm;	// 마지막수정일시
	
	public PostAtchEntity toEntity() {
		return PostAtchEntity.builder()
								.postAtchNo(postAtchNo)
								.post(PostEntity.builder().postNo(postNo).build())
								.actlFileNm(actlFileNm)
								.fileDivision(fileDivision)
								.fileUrl(fileUrl)
								.atchIdx(atchIdx)
								.registrationDate(registrationDate)
								.useYn("Y")
								.build();
	}
}