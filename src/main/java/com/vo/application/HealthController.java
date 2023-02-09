package com.vo.application;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
	
	@GetMapping("/healthCheck")
	public String heathCheck() {
		return "health OK";
	}
}
