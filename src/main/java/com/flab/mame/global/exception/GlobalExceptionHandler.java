package com.flab.mame.global.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
/*
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity handleMethodArgumentException(MethodArgumentNotValidException e) {
		*//*
	 *
	 * TODO: ErrorResponse needed
	 *
	 * *//*
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("오류");
	}*/
}
