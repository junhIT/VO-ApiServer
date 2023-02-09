package com.vo.application.service;

import com.vo.application.data.dto.MemberDto;

public interface MemberService {

	MemberDto registerMember(MemberDto req);

	MemberDto login(MemberDto req) throws Exception;

}
