package com.flab.mame.swipe;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flab.mame.global.annotation.CurrentMember;
import com.flab.mame.global.exception.ErrorCode;
import com.flab.mame.global.exception.RestApiException;
import com.flab.mame.matcheduser.Matching;
import com.flab.mame.matcheduser.MatchingRepository;
import com.flab.mame.user.domain.Member;
import com.flab.mame.user.domain.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SwipeService {

	private final SwipeRepository swipeRepository;
	private final MemberRepository memberRepository;

	private final MatchingRepository matchingRepository;

	public void swipeUser(@CurrentMember final Long swiperId, final SwipeRequest request) {
		/*
		 * TODO: 프로필 미완성시 예외처리
		 *
		 * */

		Member swiper = memberRepository.findById(swiperId)
			.orElseThrow(() -> new RestApiException(ErrorCode.MEMBER_NOT_FOUND));

		Member swipee = memberRepository.findById(request.getSwipeeId())
			.orElseThrow(() -> new RestApiException(ErrorCode.MEMBER_NOT_FOUND));

		if (swipee.getId().equals(swiper.getId())) {
			throw new RestApiException(ErrorCode.INVALID_SWIPE_REQUEST);
		}

		Swipe newSwipe = Swipe.builder()
			.swiper(swiper)
			.swipee(swipee)
			.type(request.getSwipeType())
			.build();

		swipeRepository.save(newSwipe);

		if (newSwipe.getType().equals(SwipeType.LIKE)) {
			swipeRepository.findBySwiperAndSwipee(swipee, swiper)
				.filter(swipe -> swipe.getType().equals(SwipeType.LIKE))
				.ifPresent(swipe -> {
					Matching newMatchForUser1 = Matching.builder()
						.member1(swiper)
						.member2(swipee)
						.build();

					Matching newMatchForUser2 = Matching.builder()
						.member1(swipee)
						.member2(swiper)
						.build();

					matchingRepository.save(newMatchForUser1);
					matchingRepository.save(newMatchForUser2);

				});

		}

	}
}

