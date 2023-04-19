package com.vo.application;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
	
	@GetMapping("/healthCheck")
	public String heathCheck() {
		return "health OK";
	}
	
	// 현재 환경 확인
	@GetMapping("/activeProfile")
	public String getActiveProfile() {
		return StringUtils.defaultString(System.getProperty("Spring.profiles.active"));
	}
}