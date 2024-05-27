package com.flab.mame.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flab.mame.global.exception.ErrorCode;
import com.flab.mame.global.exception.RestApiException;
import com.flab.mame.user.domain.Member;
import com.flab.mame.user.domain.UserRepository;
import com.flab.mame.user.dto.UserSignupRequest;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public void signup(final UserSignupRequest request) {

		if (userRepository.findByEmail(request.getEmail()).isPresent()) {
			throw new RestApiException(ErrorCode.EMAIL_ALREADY_USED);
		}

		final Member newMember = Member.builder()
			.email(request.getEmail())
			.password(request.getPassword())
			.build();

		userRepository.save(newMember);

	}

	public Member getUserById(final Long id) {
		/*
		 * TODO: 유저 못찾을 시 예외처리
		 * */
		final Member foundMember = userRepository.findById(id)
			.orElseThrow(() -> new RestApiException(ErrorCode.USER_NOT_FOUND));

		return foundMember;
	}

	/*public void updateUser(final Long id, UserUpdateReqeust reqeust) {
	 *//*
	 * TODO: 유저 못찾을 시 예외처리
	 * *//*
		User foundUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("유저 없음"));

		foundUser.update(reqeust);

	}*/

	public void deleteUser(final Long id) {
		/*
		 * TODO: 유저 못찾을 시 예외처리
		 * */
		final Member foundMember = userRepository.findById(id)
			.orElseThrow(() -> new RestApiException(ErrorCode.USER_NOT_FOUND));
		userRepository.delete(foundMember);

	}
}
