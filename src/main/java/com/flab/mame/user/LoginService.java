package com.flab.mame.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.flab.mame.user.domain.User;
import com.flab.mame.user.domain.UserRepository;
import com.flab.mame.user.domain.UserSessionConst;
import com.flab.mame.user.dto.UserLoginRequest;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class LoginService {

	private final HttpSession httpSession;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public void login(final UserLoginRequest reqeust) {
		/*
		 * TODO: 유저 못찾을 경우, 비밀번호 틀릴 경우, 비밀번호 검증
		 * */
		User foundUser = userRepository.findByEmail(reqeust.getEmail())
			.orElseThrow(() -> new RuntimeException("유저 못찾음"));

		if (!foundUser.getPassword().equals(reqeust.getPassword())) {
			throw new RuntimeException("비밀번호 틀림");
		}

		httpSession.setAttribute(UserSessionConst.USER_ID, foundUser.getId());
		log.info("session Id = {}", httpSession.getId());
		log.info("userId = {}", httpSession.getAttribute(UserSessionConst.USER_ID));

	}

	public void logout() {
		httpSession.invalidate();
	}
}
