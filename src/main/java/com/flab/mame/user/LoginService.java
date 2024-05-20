package com.flab.mame.user;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LoginService {

	private final HttpSession httpSession;

	private final UserRepository userRepository;

	public void login(final UserLoginRequest reqeust) {
		/*
		 * TODO: 유저 못찾을 경우, 비밀번호 틀릴 경우, 비밀번호 검증
		 * */
		User foundUser = userRepository.findByEmail(reqeust.getEmail())
			.orElseThrow(() -> new RuntimeException("유저 못찾음"));

		if (!foundUser.getPassword().equals(reqeust.getPassword())) {
			throw new RuntimeException("비밀번호 틀림");
		}

		httpSession.setAttribute("userId", foundUser.getId());

	}

	public void logout() {
		httpSession.invalidate();
	}
}
