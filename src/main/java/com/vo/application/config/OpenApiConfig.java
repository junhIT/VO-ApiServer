package com.vo.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/**
 * 클래스명 : OpenApiconfig.java
 * 클래스설명 : Swagger springdoc-ui 구성 파일
 * 작성일 : 2023-03-13
 */
@Configuration
public class OpenApiConfig {
	@Bean
	public OpenAPI openAPI() {
		Info info = new Info()
				.title("VO API Swagger")
				.version("V0.0.1")
				.description("VO 프로젝트 API 명세서입니다.");
		return new OpenAPI()
				.components(new Components())
				.info(info);
	}
}
