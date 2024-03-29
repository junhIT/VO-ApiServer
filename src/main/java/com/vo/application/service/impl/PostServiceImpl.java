package com.vo.application.service.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vo.application.common.dto.AwsS3Constant;
import com.vo.application.common.service.AwsS3Service;
import com.vo.application.common.util.DateUtil;
import com.vo.application.data.dto.PostAtchDTO;
import com.vo.application.data.dto.PostDTO;
import com.vo.application.data.dto.PostGetDetailResDTO;
import com.vo.application.data.dto.PostGetDetailResDTO.CommentList;
import com.vo.application.data.dto.PostSaveReqDTO;
import com.vo.application.data.entity.CommentAtchEntity;
import com.vo.application.data.entity.CommentEntity;
import com.vo.application.data.entity.PostEntity;
import com.vo.application.data.reprository.CommentRepository;
import com.vo.application.data.reprository.PostAtchRepository;
import com.vo.application.data.reprository.PostRepository;
import com.vo.application.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

	@Autowired
	PostRepository postRepository;

	@Autowired
	PostAtchRepository postAtchRepository;
	
	@Autowired
	CommentRepository commentRepository;

	private final AwsS3Service awsS3Service;

	/**
	 * 게시글 등록
	 * 
	 * @throws Exception
	 */
	public PostDTO registerPost(PostSaveReqDTO req, List<MultipartFile> scripts, List<MultipartFile> images)
			throws Exception {

		/* validation Check */

		if (req.getMbNo() == null) {
			throw new Exception("MbNO가 없어요");
		}

		/* 게시글 정보 저장 */
		// TODO 회원 정보 Session에서 불러오기
		PostEntity postRes = postRepository.save(req.toEntity());

		log.debug("게시글 등록 START ============================================================================");

		/* Script 파일 저장 */
		if (scripts.size() > 0) {

			int scriptIdx = 1;

			for (MultipartFile file : scripts) {

				log.debug("file UPLOAD START === {}", file.toString());

				// 파일 서버에 저장
				String fileUploadUrl = awsS3Service.uploadFile(AwsS3Constant.PATH_POST_SCRIPT, file);

				log.debug("file UPLOAD END ({})=== {}", scriptIdx, fileUploadUrl);

				/* 현재 시간 가져오는 YYYYMMDDhhmmss */
				Date nowDate = new Date();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

				// 서버에 저장된 파일 정보 DB에 저장
				PostAtchDTO postAtchDTO = PostAtchDTO.builder().postNo(postRes.getPostNo())
						.actlFileNm(file.getOriginalFilename()).fileDivision("02") // 02 : Script
						.fileUrl(fileUploadUrl).atchIdx(String.valueOf(scriptIdx++))
						.registrationDate(req.getRegistrationDate()).useYn("Y").build();

				postAtchRepository.save(postAtchDTO.toEntity());

			}
		}

		/* image 파일 저장 */
		if (images.size() > 0) {

			int imageIdx = 0;

			for (MultipartFile file : images) {

				log.debug("file UPLOAD START === {}", file.toString());

				String fileUploadUrl = awsS3Service.uploadFile(AwsS3Constant.PATH_POST_IMAGE, file);

				log.debug("file UPLOAD END ({})=== {}", imageIdx, fileUploadUrl);

				/* 현재 시간 가져오는 YYYYMMDDhhmmss */
				Date nowDate = new Date();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

				// 서버에 저장된 파일 정보 DB에 저장
				PostAtchDTO postAtchDTO = PostAtchDTO.builder().postNo(postRes.getPostNo())
						.actlFileNm(file.getOriginalFilename()).fileDivision("02") // 02 : Script
						.fileUrl(fileUploadUrl).atchIdx(String.valueOf(imageIdx++))
						.registrationDate(req.getRegistrationDate()).useYn("Y").build();

				postAtchRepository.save(postAtchDTO.toEntity());
			}
		}

		log.debug("게시글 등록 END ============================================================================");

		return PostDTO.builder().postNo(postRes.getPostNo()).build();
	}

	/**
	 * 게시글 목록 조회
	 */
	@Override
	public List<PostDTO> getPostList(Integer mbNo, String strStartDate, String strEndDate, Integer selectMbNo) {
		
		LocalDate StartDate = null;
		LocalDate EndDate = null;
		
		if( strStartDate != null ) {
			StartDate = DateUtil.parseDate(strStartDate, DateUtil.DATE_FORMAT_yyyyMMdd);
		}
		
		if( strEndDate != null ) {
			EndDate = DateUtil.parseDate(strEndDate, DateUtil.DATE_FORMAT_yyyyMMdd);
		}
		
//		Pageable pageable = PageableUtil.createPageRequest(req.getPage(), req.getPageSize());
		
		// 게시글 목록 조회
//		List<PostEntity> postEntityRes = postRepository.findPostListByPostDto(pageable, startDate, endDate, mbNo, selectMbNo);
		List<PostEntity> postEntityRes = postRepository.findPostListByPostDto(mbNo, StartDate, EndDate, selectMbNo);
//		List<PostEntity> postEntityRes = postRepository.findPostListByPostDto(mbNo, startDate, endDate, pageable);

		// Entity List to DTO List
		List<PostDTO> postDtoRes = postEntityRes.stream()
				.map(m -> PostDTO.builder().postNo(m.getPostNo()).mbNo(m.getMember().getMbNo()).title(m.getTitle())
						.content(m.getContent())
						.registrationDate(DateUtil.formatDate(m.getRegistrationDate(), DateUtil.DATE_FORMAT_yyyyMMdd))
						.closingDate(DateUtil.formatDate(m.getClosingDate(), DateUtil.DATE_FORMAT_yyyyMMdd))
						.price(m.getPrice()).recordingPlace(m.getRecordingPlace()).recordingType(m.getRecordingPlace())
						.useYn(m.getUseYn()).view(m.getView()).build())
				.collect(Collectors.toList());

		return postDtoRes;
	}

	/**
	 * 게시글 상세 조회
	 */
	@Override
	public PostGetDetailResDTO getPost(int postNo) {

		// 조회수 증가
		postRepository.updateView(postNo);

		// 게시글 상세 조회
		PostEntity postEntityRes = postRepository.getReferenceById(postNo);
		
		
		// 댓글 목록 조회 
		List<PostGetDetailResDTO.CommentList> commentList = new ArrayList<PostGetDetailResDTO.CommentList>();

		List<CommentEntity> commentEntityList = commentRepository.findByPostNoWithCommentAtch(postNo);
		
		for( CommentEntity obj : commentEntityList ) {
			PostGetDetailResDTO.CommentList cl = new PostGetDetailResDTO.CommentList();
			
			cl.setCommentNo(obj.getCommentNo());
			cl.setMbNm(obj.getMember().getName());
			cl.setRegistrationDate(DateUtil.formatDate(obj.getRegistrationDate(), DateUtil.DATE_FORMAT_yyyyMMdd));
			cl.setContent(obj.getContent());
			cl.setChoiceYn(obj.getChoiceYn());
			
			
			for( CommentAtchEntity atchObj : obj.getCommentAtchList() ) {
				PostGetDetailResDTO.CommentAtchList cal = new PostGetDetailResDTO.CommentAtchList();
				
				cal.setActlFileNm(atchObj.getActlFileNm());
				cal.setFileUrl(atchObj.getFileUrl());
				cal.setRegistrationDate(DateUtil.formatDate(atchObj.getRegistrationDate(), DateUtil.DATE_FORMAT_yyyyMMdd));
				
				cl.getCommentAtchList().add(cal);
			}
			
			commentList.add(cl);
		}
		
		PostGetDetailResDTO res = PostGetDetailResDTO.builder()
				.postNo(postEntityRes.getPostNo())
				.mbNo(postEntityRes.getMember().getMbNo())
				.title(postEntityRes.getTitle())
				.content(postEntityRes.getContent())
				.registrationDate(DateUtil.formatDate(postEntityRes.getRegistrationDate(), DateUtil.DATE_FORMAT_yyyyMMdd))
				.closingDate(DateUtil.formatDate(postEntityRes.getClosingDate(), DateUtil.DATE_FORMAT_yyyyMMdd))
				.price(postEntityRes.getPrice())
				.recordingPlace(postEntityRes.getRecordingPlace())
				.useYn(postEntityRes.getUseYn())
				.view(postEntityRes.getView())
				.build();
		
		res.setCommentList(commentList);
		
		return res; 
	}
}
