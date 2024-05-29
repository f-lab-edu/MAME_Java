package com.flab.mame.swipe;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flab.mame.global.annotation.CurrentMember;
import com.flab.mame.global.exception.ErrorCode;
import com.flab.mame.global.exception.RestApiException;
import com.flab.mame.matcheduser.MatchedUser;
import com.flab.mame.matcheduser.MatchedUserRepository;
import com.flab.mame.user.domain.Member;
import com.flab.mame.user.domain.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class SwipeService {

	private final SwipeRepository swipeRepository;
	private final MemberRepository memberRepository;

	private final MatchedUserRepository matchedUserRepository;

	public void swipeUser(@CurrentMember final Long swiperId, final SwipeRequest request) {
		/*
		 * TODO: 프로필 미완성시 예외처리
		 *
		 * */

		Member swiper = memberRepository.findById(swiperId)
			.orElseThrow(() -> new RestApiException(ErrorCode.USER_NOT_FOUND));

		Member swipee = memberRepository.findById(request.getSwipeeId())
			.orElseThrow(() -> new RestApiException(ErrorCode.USER_NOT_FOUND));

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
					MatchedUser newMatchForUser1 = MatchedUser.builder()
						.member1(swiper)
						.member2(swipee)
						.build();

					MatchedUser newMatchForUser2 = MatchedUser.builder()
						.member1(swipee)
						.member2(swiper)
						.build();

					matchedUserRepository.save(newMatchForUser1);
					matchedUserRepository.save(newMatchForUser2);
				});

		}
	}
}

