package com.vo.application.service;

import com.vo.application.data.dto.MemberDto;
import com.vo.application.data.dto.RegisterDto;

public interface MemberService {

	RegisterDto registerMember(RegisterDto req) throws Exception;

	MemberDto login(MemberDto req) throws Exception;

	MemberDto getMember(Integer mbNo) throws Exception;

}
