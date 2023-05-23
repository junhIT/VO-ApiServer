package com.vo.application.data.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostGetDetailResDTO {
	private Integer postNo;	// 게시글번호
	private Integer mbNo;	// 회원번호
	private String title;	// 제목
	private String content;	// 내용
	private String registrationDate;	// 등록일자
	private String closingDate;	// 마감일자
	private Integer	price;	//단가
	private String recordingPlace;	// 녹음장소
	private String recordingType;	// 녹음타입
	private String useYn;	// 사용여부
	private int view;	// 조회수
	private Date frstRegiDttm;	// 최초등록일시
	private Date lastChngDttm;	// 마지막수정일시
	
	@Setter
	private List<CommentList> commentList;

	@Getter
	@Setter
	@AllArgsConstructor
	public static class CommentList {
		private Integer commentNo;	// 댓글번호
		private String mbNm;	// 댓글작성자명
		private String choiceYn;	// 채택여부
		private String content;	// 내용
		private String registrationDate;	// 등록일자
		private List<CommentAtchList> commentAtchList;	// 댓글 파일첨부 목록
		
		public CommentList() {
	        commentAtchList = new ArrayList<>();    // 생성자에서 초기화
	    }
	}
	
	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	public static class CommentAtchList {
		private String actlFileNm;	// 실제파일명
		private String fileUrl;	// 파일경로
		private String registrationDate;	// 등록일자
	}
	
}
