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

	// Api 정상 Response
	public static <T> ApiResponse<T> success(T data) {
		return new ApiResponse<>(SUCCESS_STATUS, SUCCESS_CODE, data, null);
	}
	
	// Api 오류 Response
	public static <T> ApiResponse<T> fail(String message) {
		return new ApiResponse<>(FAIL_STATUS, FAIL_CODE, null, message);
	}
	
	// Api 실패 Response
	public static <T> ApiResponse<T> error(String message) {
		return new ApiResponse<>(ERROR_STATUS, ERROR_CODE, null, message);
	}
	
	private ApiResponse(String status, String statusCode, T data, String message) {
		this.status = status;
		this.statusCode = statusCode;
		this.data = data;
		this.message = message;
	}
}
