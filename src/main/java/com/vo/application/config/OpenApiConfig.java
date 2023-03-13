package com.vo.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/**
 * Ŭ������ : OpenApiconfig.java
 * Ŭ�������� : Swagger springdoc-ui ���� ����
 * �ۼ��� : 2023-03-13
 */
@Configuration
public class OpenApiConfig {
	@Bean
	public OpenAPI openAPI() {
		Info info = new Info()
				.title("VO API Swagger")
				.version("V0.0.1")
				.description("VO ������Ʈ API �����Դϴ�.");
		return new OpenAPI()
				.components(new Components())
				.info(info);
	}
}
