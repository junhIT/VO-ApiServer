package com.vo.application.common.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiResponse<T> {
	
	private static final String SUCCESS_STATUS = "SUCCESS";
	private static final String SUCCESS_CODE = "000";
	private static final String FAIL_STATUS = "FAIL";
	private static final String FAIL_CODE = "100";
	private static final String ERROR_STATUS = "ERROR";
	private static final String ERROR_CODE = "200";
	
	private String status;
	private String statusCode;
	private T data;
	private String message;

	public static <T> ApiResponse<T> success(T data) {
		return new ApiResponse<>(SUCCESS_STATUS, SUCCESS_CODE, data, null);
	}
	
	private ApiResponse(String status, String statusCode, T data, String message) {
		this.status = status;
		this.statusCode = statusCode;
		this.data = data;
		this.message = message;
	}
}
