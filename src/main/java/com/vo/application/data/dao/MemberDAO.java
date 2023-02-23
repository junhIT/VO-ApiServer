package com.vo.application.data.dao;

import com.vo.application.data.dto.MemberDTO;
import com.vo.application.data.dto.MemberRegisterReqDTO;

public interface MemberDAO {

	void registerMember(MemberRegisterReqDTO memberEntity);
	
	MemberDTO getMember(Integer mbNo);

	MemberDTO getMemberById(String id);
}
