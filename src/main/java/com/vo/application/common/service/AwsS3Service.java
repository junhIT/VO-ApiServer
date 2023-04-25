package com.vo.application.common.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class AwsS3Service {

    private final AmazonS3Client amazonS3Client;

	@Value("${cloud.aws.s3.bucket}")
	private String bucketName;
	
	public String uploadFile(String category, MultipartFile file) {
		
		log.debug("AWS S3 UploadFile START ==================================================");
		
		UUID uuid = UUID.randomUUID();	// UUID
		String originalFileName = file.getOriginalFilename();	// 실제파일명
		String fileName = category + uuid + "_" + originalFileName;	// 서버에 저장되는 파일명
		
		log.debug("AWS S3 UploadFile fileName : {} ==================================================", fileName);

		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentType(file.getContentType());

		try(InputStream inputStream = file.getInputStream()) {
			amazonS3Client.putObject(new PutObjectRequest(bucketName, fileName, inputStream, objectMetadata)
					.withCannedAcl(CannedAccessControlList.PublicRead));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		log.debug("AWS S3 UploadFile END ==================================================");

		return amazonS3Client.getUrl(bucketName, fileName).toString();
	}
}
