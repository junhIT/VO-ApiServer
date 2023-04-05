package com.vo.application.data.dao;

import com.vo.application.data.dto.MemberAtchDTO;

/**
 * 회원첨부내역 
 * DAO 
 */
public interface MemberAtchDAO {

	// 회원첨부내역 저장
	MemberAtchDTO saveMemberAtch(MemberAtchDTO req);
}
