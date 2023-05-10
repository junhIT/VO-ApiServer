package com.vo.application.data.entity;

import java.time.LocalDate;
import java.util.Date;

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
@Table(name = "POST_ATCH_TB")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PostAtchEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int postAtchNo;	// 게시글첨부내역번호
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "postNo")		// 게시글번호
	private PostEntity post;	// 게시글테이블
	
	private String actlFileNm;	// 실제파일명
	private String fileDivision;	// 파일구분
	private String fileUrl;	// 파일위치
	private LocalDate registrationDate;	// 등록일자
	private String atchIdx;	// 첨부순번
	private String useYn;	// 사용여부
	private Date frstRegiDttm;	// 최초등록일시
	private Date lastChngDttm;	// 마지막수정일시
}
