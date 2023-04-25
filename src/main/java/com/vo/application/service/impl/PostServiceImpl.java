package com.vo.application.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vo.application.common.service.AwsS3Service;
import com.vo.application.data.dto.PostDTO;
import com.vo.application.data.dto.PostSaveReqDTO;
import com.vo.application.data.entity.PostEntity;
import com.vo.application.data.reprository.PostRepository;
import com.vo.application.service.PostService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService{
	
	@Autowired
	PostRepository postRepository;
	
    private final AwsS3Service awsS3Service;

    /**
	 * 게시글 등록 
	 * @throws Exception 
	 */
	public PostDTO registerPost(PostSaveReqDTO req) throws Exception {
		
		/* validation Check */
		
		if(req.getMbNo() == null) {
			throw new Exception("MbNO가 없어요");
		}
		
		// TODO 회원 정보 Session에서 불러오기
		PostEntity postRes = postRepository.save(req.toEntity());
		
		return PostDTO.builder().postNo(postRes.getPostNo()).build();
	}

	/**
	 * 게시글 목록 조회
	 */
	@Override
	public List<PostDTO> getPostList() {
		
		List<PostEntity> postEntityRes = postRepository.findAll();
		
		// Entity List to DTO List
		List<PostDTO> postDtoRes = postEntityRes.stream()
												.map(m -> PostDTO.builder()
																		.postNo(m.getPostNo())
																		.memberNo(m.getMember().getMbNo())
																		.title(m.getTitle())
																		.content(m.getContent())
																		.registrationDate(m.getRegistrationDate())
																		.closingDate(m.getClosingDate())
																		.price(m.getPrice())
																		.recordingPlace(m.getRecordingPlace())
																		.useYn(m.getUseYn())
																		.view(m.getView())
																		.build())
												.collect(Collectors.toList());
		
		return postDtoRes;
	}

	/**
	 * 게시글 상세 조회
	 */
	@Override
	public PostDTO getPost(int postNo) {
		
		PostEntity postEntityRes = postRepository.getReferenceById(postNo);
		
		postRepository.updateView(postNo);
		
		return PostDTO.builder()
					.postNo(postEntityRes.getPostNo())
					.memberNo(postEntityRes.getMember().getMbNo())
					.title(postEntityRes.getTitle())
					.content(postEntityRes.getContent())
					.registrationDate(postEntityRes.getRegistrationDate())
					.closingDate(postEntityRes.getClosingDate())
					.price(postEntityRes.getPrice())
					.recordingPlace(postEntityRes.getRecordingPlace())
					.useYn(postEntityRes.getUseYn())
					.view(postEntityRes.getView())
					.build();
	}

	/**
	 * 게시글 파일 등록
	 */
	public String uploadFile(MultipartFile file) {
		
		// AWS S3에 파일을 업로드 하는 예제임
		return awsS3Service.uploadFile("post/script/", file);
	}
}
