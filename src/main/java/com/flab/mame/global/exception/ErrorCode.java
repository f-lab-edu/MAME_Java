package com.flab.mame.global.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

	// Member
	MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 유저가 존재하지 않음"),
	INVALID_LOGIN_REQUEST(HttpStatus.UNAUTHORIZED, "유효한 로그인 정보가 아님"),
	EMAIL_ALREADY_USED(HttpStatus.CONFLICT, "이미 존재하는 이메일"),

	LOGIN_REQUIRED(HttpStatus.UNAUTHORIZED, "로그인 필요"),

	// Profile
	PROFILE_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 프로필"),
	PROFILE_INCOMPLETED(HttpStatus.NOT_FOUND, "프로필 미완성"),
	PROFILE_ALREADY_EXIST(HttpStatus.CONFLICT, "프로필 이미 존재함"),

	// Profile Image
	PROFILE_IMAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 프로필 사진"),

	// Swipe
	SWIPE_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 SwipeeId"),
	INVALID_SWIPE_REQUEST(HttpStatus.CONFLICT, "본인을 SWIPE할수 없습니다"),
	SWIPE_ALREADY_EXIST(HttpStatus.CONFLICT, "이미 Swipe한 Profile"),

	// UserMatch
	USER_MATCH_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 User Match");
	private final HttpStatus httpStatus;
	private final String message;

}
