package com.flab.mame.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.flab.mame.global.exception.ErrorCode;
import com.flab.mame.global.exception.RestApiException;
import com.flab.mame.user.domain.Member;
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
		final Member foundMember = userRepository.findByEmail(reqeust.getEmail())
			.orElseThrow(() -> new RestApiException(ErrorCode.USER_NOT_FOUND));
		/*
		 * TODO: 비밀번호 암호화 및 매칭
		 * */
		if (!foundMember.getPassword().equals(reqeust.getPassword())) {
			throw new RestApiException(ErrorCode.INVALID_LOGIN_REQUEST);
		}

		httpSession.setAttribute(UserSessionConst.USER_ID, foundMember.getId());

		log.info("session Id = {}", httpSession.getId());
		log.info("userId = {}", httpSession.getAttribute(UserSessionConst.USER_ID));

	}

	public void logout() {
		httpSession.invalidate();
	}
}
