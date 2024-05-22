package com.flab.mame.global;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

	USER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 유저가 존재하지 않음"),
	INVALID_LOGIN_REQUEST(HttpStatus.UNAUTHORIZED, "유효한 로그인 정보가 아님"),
	EMAIL_ALREADY_USED(HttpStatus.CONFLICT, "이미 존재하는 이메일");

	private final HttpStatus httpStatus;
	private final String message;

}
