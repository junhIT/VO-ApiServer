package com.vo.application.service.impl;

import java.io.File;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vo.application.data.dto.MemberAtchDTO;
import com.vo.application.data.dto.MemberDTO;
import com.vo.application.data.dto.MemberRegisterReqDTO;
import com.vo.application.data.entity.MemberEntity;
import com.vo.application.data.reprository.MemberAtchRepository;
import com.vo.application.data.reprository.MemberRepository;
import com.vo.application.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	private MemberAtchRepository memberAtchRepository;

	/**
	 * 회원가입 
	 */
	public void registerMember(MemberRegisterReqDTO req) throws Exception {
		
		log.debug(":::::::::::::::::::::::::::::::: 회원 가입 ::::::::::::::::::::::::::::::::::");
		
		/* validation Check */
		
		// 비밀번호, 비밀번호확인이 일치하는지 확인
		if( !req.getPassword().equals(req.getPasswordConfirm()) ) {
			throw new Exception("비밀번호가 일치하지 않습니다.");
		}
		
		// 이미 존재하는 ID인지 확인
		if( !Objects.isNull(memberRepository.findById(req.getId())) ) {
			throw new Exception("이미 존재하는 ID입니다.");
		};
		
		/* Member Create */
		MemberEntity entityReq = req.toEntity();
		
		entityReq.setRegistrationDttm(new Date());
		entityReq.setFrstRegiDttm(new Date());
		entityReq.setMbClsfc("1");	// 정상
		
		memberRepository.save(entityReq);
	}

	/**
	 * 회원 로그인 
	 */
	public void login(MemberDTO req) throws Exception {
		
		log.debug(":::::::::::::::::::::::::::::::: 회원 로그인 ::::::::::::::::::::::::::::::::::");
		log.debug(req.toString());
		
		/* validation Check */

		MemberEntity entityRes = memberRepository.findById(req.getId());
		
		MemberDTO memberRes = MemberDTO.builder()
									.mbNo(entityRes.getMbNo())
									.id(entityRes.getId())
									.password(entityRes.getPassword())
									.name(entityRes.getName())
									.careerStartDate(entityRes.getCareerStartDate())
									.mbClsfc(entityRes.getMbClsfc())
									.registrationDttm(entityRes.getRegistrationDttm())
									.withdrawalDttm(entityRes.getWithdrawalDttm())
									.build();
		
		// 조회된 데이터가 없을 경우
		if(memberRes == null) {
			throw new Exception("회원 정보가 없습니다.");
		}

		// 비밀번호가 일치하지 않을 경우
		if( !memberRes.getPassword().equals(req.getPassword()) ) {
			throw new Exception("비밀번호가 일치하지 않습니다.");
		}
	}
	
	/**
	 * 회원 정보 조회
	 */
	public MemberDTO getMember(String id) throws Exception {

		MemberEntity entityRes = memberRepository.findById(id);
		
		return MemberDTO.builder()
				.mbNo(entityRes.getMbNo())
				.id(entityRes.getId())
				.password(entityRes.getPassword())
				.name(entityRes.getName())
				.careerStartDate(entityRes.getCareerStartDate())
				.mbClsfc(entityRes.getMbClsfc())
				.registrationDttm(entityRes.getRegistrationDttm())
				.withdrawalDttm(entityRes.getWithdrawalDttm())
				.build();
	}
	
	/**
	 * 회원 정보 수정
	 */
	public MemberDTO updateMember(MemberDTO req, MultipartFile file) throws Exception {
		
		log.debug(":::::::::::::::::::::::::::::::: 회원정보 수정 START ::::::::::::::::::::::::::::::::::");
		log.debug(req.toString());

		MemberEntity entityRes = memberRepository.findById(req.getId());

		MemberDTO memberRes = MemberDTO.builder()
				.mbNo(entityRes.getMbNo())
				.id(entityRes.getId())
				.password(entityRes.getPassword())
				.name(entityRes.getName())
				.careerStartDate(entityRes.getCareerStartDate())
				.mbClsfc(entityRes.getMbClsfc())
				.registrationDttm(entityRes.getRegistrationDttm())
				.withdrawalDttm(entityRes.getWithdrawalDttm())
				.build();
		
		// 조회된 데이터가 없을 경우
		if( memberRes == null ) {
			throw new Exception("회원 정보가 없습니다.");
		}
		
		// 비밀번호가 일치하지 않을 경우
		if( !memberRes.getPassword().equals(req.getPassword()) ) {
			throw new Exception("비밀번호가 일치하지 않습니다.");
		}
		

		MemberEntity entityUpdateRes = memberRepository.save(memberRes.toEntity());
		
		MemberDTO updateMemberRes = MemberDTO.builder()
											.id(entityUpdateRes.getId())
											.password(entityUpdateRes.getPassword())
											.name(entityUpdateRes.getName())
											.careerStartDate(entityUpdateRes.getCareerStartDate())
											.mbClsfc(entityUpdateRes.getMbClsfc())
											.registrationDttm(entityUpdateRes.getRegistrationDttm())
											.withdrawalDttm(entityUpdateRes.getWithdrawalDttm())
											.build();	
		
		// 프로필파일이 있을 경우 저장
		if( Objects.nonNull(file) && file.getSize() > 0 ) {
			log.debug(":::::::::::::::::::::::::::::::: 프로필 저장 START ::::::::::::::::::::::::::::::::::");
			
			// @TODO : 파일저장경로 properties에 설정.
			String filePath = "/home/ubuntu/vo/upload";	// 파일저장경로
			
			// local일 경우
			if(StringUtils.defaultString(System.getProperty("Spring.profiles.active")).equals("local")) {
				filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\profiles";
			}

			UUID uuid = UUID.randomUUID();	// UUID
			String originalFileName = file.getOriginalFilename();	// 실제파일명
			String fileName = uuid + "_" + originalFileName;	// 서버에 저장되는 파일명
			
			log.debug("filePath : {}, uuid : {}, originaFileName : {}, fileName : {}", filePath, uuid, originalFileName, fileName);
			
			// 파일 서버에 저장
			File saveFile = new File(filePath, fileName);
			file.transferTo(saveFile);
			
			// 서버에 저장된 파일 정보 DB에 저장
			MemberAtchDTO memberAtchReq = MemberAtchDTO.builder()
														.member(MemberDTO.builder().mbNo(memberRes.getMbNo()).build())
														.actlFileNm(originalFileName)
														.fileNm(fileName)
														.fileUrl(filePath)
														.useYn("Y")
														.registrationDate("")
														.build();
			
			
			memberAtchRepository.save(memberAtchReq.toEntity());
		}

		log.debug(":::::::::::::::::::::::::::::::: 회원정보 수정 END ::::::::::::::::::::::::::::::::::");
		
		return MemberDTO.builder().build();
	}
}