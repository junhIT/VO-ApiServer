package com.vo.application.data.entity;

import java.time.LocalDate;
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
import lombok.Setter;

@Entity
@Table(name = "COMMENT_TB")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CommentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int commentNo;	// 게시글번호
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "mbNo")		// 회원번호(댓글작성자)
	private MemberEntity member;	// 회원테이블
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "postNo")		// 게시글번호
	private PostEntity post;	//  게시글
	
	private LocalDate registrationDate;	// 등록일자
	private String content;	// 내용
	private String choiceYn;	// 채택여부
	private String useYn;	// 사용여부
	private Date frstRegiDttm;	// 최초등록일시
	private Date lastChngDttm;	// 마지막수정일시
}
