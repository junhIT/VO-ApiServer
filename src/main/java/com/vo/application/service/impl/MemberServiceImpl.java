package com.vo.application.service.impl;

import java.io.File;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vo.application.data.dao.MemberAtchDAO;
import com.vo.application.data.dao.MemberDAO;
import com.vo.application.data.dto.MemberAtchDTO;
import com.vo.application.data.dto.MemberDTO;
import com.vo.application.data.dto.MemberRegisterReqDTO;
import com.vo.application.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberDAO memberDao;
	
	@Autowired
	private MemberAtchDAO memberAtchDao;

	/**
	 * 회원가입 
	 */
	public void registerMember(MemberRegisterReqDTO req) throws Exception {
		
		log.debug(":::::::::::::::::::::::::::::::: 회원 가입 ::::::::::::::::::::::::::::::::::");
		log.debug(req.toString());

		if( !req.getPassword().equals(req.getPasswordConfirm()) ) {
			throw new Exception("비밀번호가 일치하지 않습니다.");
		}
		
		if( memberDao.getMemberById(req.getId()) != null ) {
			throw new Exception("이미 존재하는 ID입니다.");
		}
		
		memberDao.registerMember(req);
	}

	/**
	 * 회원 로그인 
	 */
	public void login(MemberDTO req) throws Exception {
		
		log.debug(":::::::::::::::::::::::::::::::: 회원 로그인 ::::::::::::::::::::::::::::::::::");
		log.debug(req.toString());
		
		MemberDTO memberDto = memberDao.getMemberById(req.getId());

		// 조회된 데이터가 없을 경우
		if(memberDto == null) {
			throw new Exception("회원 정보가 없습니다.");
		}

		// 비밀번호가 일치하지 않을 경우
		if( !memberDto.getPassword().equals(req.getPassword()) ) {
			throw new Exception("비밀번호가 일치하지 않습니다.");
		}
	}
	
	/**
	 * 회원 정보 조회
	 */
	public MemberDTO getMember(String id) throws Exception {

		log.debug(":::::::::::::::::::::::::::::::: 회원정보 조회 ::::::::::::::::::::::::::::::::::");

		return memberDao.getMemberById(id);
	}
	
	/**
	 * 회원 정보 수정
	 */
	public MemberDTO updateMember(MemberDTO req, MultipartFile file) throws Exception {
		
		log.debug(":::::::::::::::::::::::::::::::: 회원정보 수정 START ::::::::::::::::::::::::::::::::::");
		log.debug(req.toString());

		MemberDTO memberDto = memberDao.getMemberById(req.getId());

		// 조회된 데이터가 없을 경우
		if( memberDto == null ) {
			throw new Exception("회원 정보가 없습니다.");
		}
		
		// 비밀번호가 일치하지 않을 경우
		if( !memberDto.getPassword().equals(req.getPassword()) ) {
			throw new Exception("비밀번호가 일치하지 않습니다.");
		}
		
		MemberDTO memberRes = memberDao.updateMember(req);
		
		// 프로필파일이 있을 경우 저장
		if( Objects.nonNull(file) && file.getSize() > 0 ) {
			log.debug(":::::::::::::::::::::::::::::::: 프로필 저장 START ::::::::::::::::::::::::::::::::::");
			
			// @TODO : 파일저장경로 properties에 설정.
			String filePath = "/home/ubuntu/vo/upload";	// 파일저장경로
			
			// local일 경우
			if(System.getProperty("Spring.profiles.active").toString().equals("local")) {
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
			MemberAtchDTO memberAtchRes = memberAtchDao.saveMemberAtch(MemberAtchDTO.builder()
														.member(MemberDTO.builder().mbNo(memberDto.getMbNo()).build())
														.actlFileNm(originalFileName)
														.fileNm(fileName)
														.fileUrl(filePath)
														.build());
		}

		log.debug(":::::::::::::::::::::::::::::::: 회원정보 수정 END ::::::::::::::::::::::::::::::::::");
		
		return MemberDTO.builder().build();
	}
}