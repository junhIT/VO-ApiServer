package com.vo.application.data.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MB_TB")
@Builder
public class MemberEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mbNo;	// 회원번호
	
	@Column(unique = true)
	private String id;	// 아이디
	private String password;	// 비밀번호
	private String name;	// 이름
	private String careerStartDate;	// 경력시작일자
	private String mbClsfc;	// 회원구분
	private Date registrationDttm;	// 등록일시
	private Date withdrawalDttm;	// 탈퇴일시
	private Date frstRegiDttm;	// 최초등록일시
	private Date lastChngDttm;	// 마지막수정일시
	
}
