package com.flab.mame.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class LoginController {

	private final LoginService loginService;

	@PostMapping("/login")
	public void login(@RequestBody @Valid final UserLoginRequest reqeust) {
		log.info("request={}", reqeust);
		loginService.login(reqeust);
	}

	@GetMapping("/logout")
	public void logout() {
		loginService.logout();
	}

	/*
	 * TODO: MethodArgumentNotvalidException 핸들러 만들기
	 *
	 * */
}
