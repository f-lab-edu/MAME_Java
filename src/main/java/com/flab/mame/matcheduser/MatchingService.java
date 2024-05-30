package com.flab.mame.matcheduser;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flab.mame.user.domain.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class MatchingService {

	private final MatchingRepository matchingRepository;

	private final MemberRepository memberRepository;

	public List<MatchingResponse> getAllUserMatchesByUser1Id(final Long id) {
		// final Member foundMember = memberRepository.findById(id)
		// 	.orElseThrow(() -> new RestApiException(ErrorCode.MEMBER_NOT_FOUND));
		// final List<MatchingResponse> matches = matchingRepository.findAllByMember1(foundMember).stream()
		// 	.map(MatchingResponse::new)
		// 	.collect(Collectors.toList());
		//
		// log.info("matches size : {}", matches.size());
		// return matches;
		return null;
	}

	// public void deleteUserMatchByUser1Id(Long id) {
	// 	final Member foundMember1 = memberRepository.findById(id)
	// 		.orElseThrow(() -> new RestApiException(ErrorCode.MEMBER_NOT_FOUND));
	// 	final Matching foundUserMatchForMatching1 = matchingRepository.findByMember1(foundMember1)
	// 		.orElseThrow(() -> new RestApiException(ErrorCode.USER_MATCH_NOT_FOUND));
	//
	// 	final Member member2 = foundUserMatchForMatching1.getMember2();
	// 	Matching foundUserMatchForMatching2 = matchingRepository.findByMember1(member2)
	// 		.orElseThrow(() -> new RestApiException(ErrorCode.USER_MATCH_NOT_FOUND));
	//
	// 	matchingRepository.delete(foundUserMatchForMatching1);
	// 	matchingRepository.delete(foundUserMatchForMatching2);
	// }
}
