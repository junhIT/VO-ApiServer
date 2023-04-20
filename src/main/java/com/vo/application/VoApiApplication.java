package com.vo.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class VoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoApiApplication.class, args);
	}

}
