package com.vo.application.data.reprository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vo.application.data.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, Integer>{

	MemberEntity findById(String id);

}