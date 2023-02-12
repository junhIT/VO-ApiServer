package com.vo.application.data.dao;

import com.vo.application.data.entity.MemberEntity;

public interface MemberDAO {

	MemberEntity registerMember(MemberEntity memberEntity);
	
	MemberEntity getMember(Integer mbNo);

	MemberEntity login(String id);
}
