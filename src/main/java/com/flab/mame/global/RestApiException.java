package com.flab.mame.global;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class RestApiException extends RuntimeException {

	private final HttpStatus httpStatus;
	private final String message;

	public RestApiException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.httpStatus = errorCode.getHttpStatus();
		this.message = errorCode.getMessage();
	}
}
