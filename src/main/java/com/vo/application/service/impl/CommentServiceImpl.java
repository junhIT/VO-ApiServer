package com.vo.application.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vo.application.common.dto.AwsS3Constant;
import com.vo.application.common.service.AwsS3Service;
import com.vo.application.common.util.DateUtil;
import com.vo.application.data.dto.CommentSaveReqDTO;
import com.vo.application.data.entity.CommentAtchEntity;
import com.vo.application.data.entity.CommentEntity;
import com.vo.application.data.reprository.CommentAtchRepository;
import com.vo.application.data.reprository.CommentRepository;
import com.vo.application.service.CommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentRepository commentRepository;

	@Autowired
	CommentAtchRepository commentAtchRepository;

	private final AwsS3Service awsS3Service;

	/**
	 * 댓글 등록
	 */
	public void saveComment(CommentSaveReqDTO req, List<MultipartFile> files) throws Exception {

		/* validation Check */

		/* 댓글 정보 저장 */
		// TODO 회원 정보 Session에서 불러오기
		CommentEntity commentRes = commentRepository.save(req.toEntity());

		log.debug("댓글 등록 START ============================================================================");

		/* files 파일 저장 */
		if (files.size() > 0) {

			int scriptIdx = 1;

			for (MultipartFile file : files) {

				log.debug("file UPLOAD START === {}", file.toString());

				// 파일 서버에 저장
				String fileUploadUrl = awsS3Service.uploadFile(AwsS3Constant.PATH_COMMENT_RECORDING, file);

				log.debug("file UPLOAD END ({})=== {}", scriptIdx, fileUploadUrl);

				// 서버에 저장된 파일 정보 DB에 저장
				CommentAtchEntity commentAtchEntity = CommentAtchEntity.builder()
																	.comment(CommentEntity.builder().commentNo(commentRes.getCommentNo()).build())
																	.actlFileNm(file.getOriginalFilename())
																	.fileUrl(fileUploadUrl)
																	.registrationDate(LocalDate.now())
																	.useYn("Y")
																	.build(); 

				commentAtchRepository.save(commentAtchEntity);

			}
		}

		log.debug("댓글 등록 END ============================================================================");

	}

}
