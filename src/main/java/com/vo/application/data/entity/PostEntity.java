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
	private int postNo;	// 게시글번호
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "mbNo")		// 회원번호(글작성자)
	private MemberEntity member;	// 회원테이블
	
	private String title;	// 제목
	private String content;	// 내용
	private String registrationDate;	// 등록일자
	private String closingDate;	// 마감일자
	private int	price;	//단가
	private String recordingPlace;	// 녹음장소
	private String useYn;	// 사용여부
	
	@Column(columnDefinition = "integer default 0", nullable = false)
	private int view;	// 조회수
	private Date frstRegiDttm;	// 최초등록일시
	private Date lastChngDttm;	// 마지막수정일시
}
