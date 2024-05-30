package com.flab.mame.swipe;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flab.mame.global.exception.ErrorCode;
import com.flab.mame.global.exception.RestApiException;
import com.flab.mame.matcheduser.Matching;
import com.flab.mame.matcheduser.MatchingRepository;
import com.flab.mame.profile.domain.Profile;
import com.flab.mame.profile.domain.ProfileRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SwipeService {

	private final SwipeRepository swipeRepository;

	private final ProfileRepository profileRepository;

	private final MatchingRepository matchingRepository;

	public void swipeProfile(final Long swiperId, final SwipeRequest request) {
		/*
		 * TODO: 프로필 미완성시 예외처리
		 *
		 * */
		if (swiperId.equals(request.getSwipeeId())) {
			throw new RestApiException(ErrorCode.INVALID_SWIPE_REQUEST);
		}

		final Profile swiper = profileRepository.findById(swiperId).orElseThrow(
			() -> new RestApiException(ErrorCode.PROFILE_NOT_FOUND)
		);

		final Profile swipee = profileRepository.findById(request.getSwipeeId()).orElseThrow(
			() -> new RestApiException(ErrorCode.PROFILE_NOT_FOUND)
		);

		if (swipeRepository.findBySwiperAndSwipee(swiper, swipee).isPresent()) {
			throw new RestApiException(ErrorCode.SWIPE_ALREADY_EXIST);
		}

		Swipe newSwipe = Swipe.builder()
			.swiper(swiper)
			.swipee(swipee)
			.swipeType(request.getSwipeType())
			.build();

		swipeRepository.save(newSwipe);

		if (request.getSwipeType().equals(SwipeType.LIKE)) {
			swipeRepository.findBySwiperAndSwipee(swipee, swiper)
				.filter(swipe -> swipe.getSwipeType().equals(SwipeType.LIKE))
				.ifPresent(swipe -> {
					Matching newMatching = Matching.builder()
						.profile1(swiper)
						.profile2(swipee)
						.build();
					matchingRepository.save(newMatching);
				});

		}

	}
}

