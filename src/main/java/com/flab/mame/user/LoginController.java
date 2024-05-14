package com.flab.mame.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class LoginController {

	private final LoginService loginService;

	@PostMapping("/login")
	public void login(@RequestBody final UserLoginReqeust reqeust) {
		loginService.login(reqeust);
	}

	@GetMapping("/logout")
	public void logout() {
		loginService.logout();
	}
}
