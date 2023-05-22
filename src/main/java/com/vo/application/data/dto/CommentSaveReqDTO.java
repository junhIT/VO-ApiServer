package com.vo.application.data.dto;

import java.time.LocalDate;
import java.util.Date;

import com.vo.application.common.util.DateUtil;
import com.vo.application.data.entity.CommentEntity;
import com.vo.application.data.entity.MemberEntity;
import com.vo.application.data.entity.PostEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class CommentSaveReqDTO extends CommonDTO{
	
//	private String commentNo;	// 댓글번호
	private String postNo;	// 게시글번호
	private String mbNo;	// 댓글작성자
//	private String registrationDate;	// 등록일자
	private String content;	// 내용
	
	public CommentEntity toEntity() {
		return CommentEntity.builder()
//							.commentNo(Integer.parseInt(commentNo))
							.post(PostEntity.builder().postNo(Integer.parseInt(postNo)).build())
							.member(MemberEntity.builder().mbNo(Integer.parseInt(mbNo)).build())
//							.registrationDate(DateUtil.getCurrentDate_yyyy_MM_dd())
							.registrationDate(LocalDate.now())
							.content(content)
							.choiceYn("N")
							.useYn("Y")
							.build();
	}
}
