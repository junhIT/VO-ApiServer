package com.vo.application.service;

import com.vo.application.data.dto.MemberDto;

public interface MemberService {

	MemberDto registerMember(MemberDto req) throws Exception;

	MemberDto login(MemberDto req) throws Exception;

	MemberDto getMember(Integer mbNo) throws Exception;

}
