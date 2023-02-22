package com.vo.application.service;

import com.vo.application.data.dto.MemberDTO;
import com.vo.application.data.dto.MemberRegisterReqDTO;

public interface MemberService {

	void registerMember(MemberRegisterReqDTO req) throws Exception;

	void login(MemberDTO req) throws Exception;

	MemberDTO getMember(Integer mbNo) throws Exception;

}
