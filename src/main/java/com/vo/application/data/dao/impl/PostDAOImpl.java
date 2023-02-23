package com.vo.application.data.dao.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vo.application.data.dao.PostDAO;
import com.vo.application.data.dto.PostDTO;
import com.vo.application.data.dto.PostSaveReqDTO;
import com.vo.application.data.entity.PostEntity;
import com.vo.application.data.reprository.PostRepository;

@Service
public class PostDAOImpl implements PostDAO {
	
	@Autowired
	PostRepository postRepository;
	
	/**
	 * �Խñ� ����
	 */
	@Override
	public PostDTO savePost(PostSaveReqDTO req) {
		
		PostEntity postRes = postRepository.save(req.toEntity());
		
		return PostDTO.builder().postNo(postRes.getPostNo()).build();
	}

	/**
	 * �Խñ� ��� ��ȸ
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
																		.build())
												.collect(Collectors.toList());
		
		return postDtoRes;
	}

	/**
	 * �Խñ� �� ��ȸ
	 */
	@Override
	public PostDTO getPost(int postNo) {
		PostEntity postEntityRes = postRepository.getReferenceById(postNo);
		
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
					.build();
	}
}