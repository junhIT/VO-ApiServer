package com.vo.application.aop;

import java.util.Map;
import java.util.StringJoiner;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;

@Component
@Aspect
public class LoggingAspect {
	private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	
	@Pointcut("within(com.vo.application.controller..*)")
	public void onRequest() {}
	
	@Around("com.vo.application.aop.LoggingAspect.onRequest()")
	public Object doLogging(ProceedingJoinPoint pjp) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		
		Map<String, String[]> paramMap = request.getParameterMap();
		String params= "";
		
		if( !paramMap.isEmpty() ) {
//			params = " [" + paramMapToString(paramMap) + "]";
		}
		
		long start = System.currentTimeMillis();
		try {
			return pjp.proceed(pjp.getArgs());
		} finally {
			long end = System.currentTimeMillis();
			logger.info("\n {}ms \n Request : {} {} {} < {} \n {}ms", start, request.getMethod(), request.getRequestURI(), null, request.getRemoteHost(), end);
		}
	}
	
}
