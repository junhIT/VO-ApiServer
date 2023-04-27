package com.vo.application.data.entity;

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
@Table(name = "MB_ATCH_TB")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MemberAtchEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mbAtchNo;	// 회원첨부내역번호
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "mbNo")		// 회원번호(글작성자)
	private MemberEntity member;	// 회원테이블
	
	private String actlFileNm;	// 실제파일명
	private String fileNm;	// 파일명
	private String fileUrl;	// 파일위치
	private String registrationDate;	// 등록일자
	private String useYn;	// 사용여부
	private Date frstRegiDttm;	// 최초등록일시
	private Date lastChngDttm;	// 마지막수정일시
}