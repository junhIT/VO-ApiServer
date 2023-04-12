package com.vo.application.data.reprository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vo.application.data.entity.MemberAtchEntity;

/**
 * ȸ��÷�γ��� ���̺� Repository 
 */
public interface MemberAtchRepository extends JpaRepository<MemberAtchEntity, Integer>{
	
	// ȸ����ȣ�� ȸ��÷�γ��� ���� ��������
	MemberAtchEntity findByMember_MbNo(int mbNo);
}
