package com.vo.application.data.reprository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vo.application.data.entity.PostEntity;

import jakarta.transaction.Transactional;

public interface PostRepository extends JpaRepository<PostEntity, Integer>{

	@Transactional
	@Modifying
	@Query("update PostEntity set view = view + 1 where postNo = :postNo")
	int updateView(@Param(value = "postNo") int postNo);
	
	/**
	 *  게시글 목록 조회 
	 * @param startDate			조회시작일자
	 * @param endDate			조회종료일자
	 * @return List<PostEntity>
	 */
	@Query("SELECT p "
			+ "FROM PostEntity p "
			+ "WHERE p.useYn='Y' "
			+ "AND (:mbNo is null OR p.member.mbNo = :mbNo)"
			+ "AND date(p.registrationDate) >= :startDate "
			+ "AND date(p.registrationDate) <= :endDate ")
//			+ "AND (:startDate is null OR date(p.registrationDate) >= :startDate) "
//			+ "AND (:endDate is null OR date(p.registrationDate) <= :endDate) ")
//			+ "AND (:selectMbNo is null OR p.selectMbNo = :selectMbNo)")
	List<PostEntity> findPostListByPostDto(
			@Param("mbNo")Integer mbNo,
			@Param("startDate")LocalDate startDate,
			@Param("endDate")LocalDate endDate
//			Pageable pageable
//			@Param("selectMbNo")Integer selectMbNo
			);
}
