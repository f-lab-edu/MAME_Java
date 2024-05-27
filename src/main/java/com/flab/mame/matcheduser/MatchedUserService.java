package com.flab.mame.matcheduser;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flab.mame.global.exception.ErrorCode;
import com.flab.mame.global.exception.RestApiException;
import com.flab.mame.user.domain.Member;
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
		final Member foundMember = userRepository.findById(id)
			.orElseThrow(() -> new RestApiException(ErrorCode.USER_NOT_FOUND));
		final List<MatchedUserResponse> matches = matchedUserRepository.findAllByMember1(foundMember).stream()
			.map(MatchedUserResponse::new)
			.collect(Collectors.toList());

		log.info("matches size : {}", matches.size());
		return matches;
	}

	public void deleteUserMatchByUser1Id(Long id) {
		final Member foundMember1 = userRepository.findById(id)
			.orElseThrow(() -> new RestApiException(ErrorCode.USER_NOT_FOUND));
		final MatchedUser foundUserMatchForMatchedUser1 = matchedUserRepository.findByMember1(foundMember1)
			.orElseThrow(() -> new RestApiException(ErrorCode.USER_MATCH_NOT_FOUND));

		final Member member2 = foundUserMatchForMatchedUser1.getMember2();
		MatchedUser foundUserMatchForMatchedUser2 = matchedUserRepository.findByMember1(member2)
			.orElseThrow(() -> new RestApiException(ErrorCode.USER_MATCH_NOT_FOUND));

		matchedUserRepository.delete(foundUserMatchForMatchedUser1);
		matchedUserRepository.delete(foundUserMatchForMatchedUser2);

	}
}
