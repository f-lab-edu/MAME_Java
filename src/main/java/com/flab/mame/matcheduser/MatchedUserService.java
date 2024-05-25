package com.flab.mame.matcheduser;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flab.mame.global.exception.ErrorCode;
import com.flab.mame.global.exception.RestApiException;
import com.flab.mame.user.domain.User;
import com.flab.mame.user.domain.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class MatchedUserService {

	private final MatchedUserRepository matchedUserRepository;

	private final UserRepository userRepository;

	public List<MatchedUserResponse> getAllUserMatchesByUser1Id(final Long id) {
		final User foundUser = userRepository.findById(id)
			.orElseThrow(() -> new RestApiException(ErrorCode.USER_NOT_FOUND));
		final List<MatchedUserResponse> matches = matchedUserRepository.findAllByUser1(foundUser).stream()
			.map(MatchedUserResponse::new)
			.collect(Collectors.toList());

		log.info("matches size : {}", matches.size());
		return matches;
	}

	public void deleteUserMatchByUser1Id(Long id) {
		final User foundUser1 = userRepository.findById(id)
			.orElseThrow(() -> new RestApiException(ErrorCode.USER_NOT_FOUND));
		final MatchedUser foundUserMatchForMatchedUser1 = matchedUserRepository.findByUser1(foundUser1)
			.orElseThrow(() -> new RestApiException(ErrorCode.USER_MATCH_NOT_FOUND));

		final User user2 = foundUserMatchForMatchedUser1.getUser2();
		MatchedUser foundUserMatchForMatchedUser2 = matchedUserRepository.findByUser1(user2)
			.orElseThrow(() -> new RestApiException(ErrorCode.USER_MATCH_NOT_FOUND));

		matchedUserRepository.delete(foundUserMatchForMatchedUser1);
		matchedUserRepository.delete(foundUserMatchForMatchedUser2);

	}
}
