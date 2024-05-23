package com.flab.mame.swipe;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flab.mame.global.exception.ErrorCode;
import com.flab.mame.global.exception.RestApiException;
import com.flab.mame.user.domain.User;
import com.flab.mame.user.domain.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class SwipeService {

	private final SwipeRepository swipeRepository;
	private final UserRepository userRepository;

	public void swipeUser(final Long swiperId, final SwipeRequest request) {
		User swiper = userRepository.findById(swiperId)
			.orElseThrow(() -> new RestApiException(ErrorCode.USER_NOT_FOUND));

		User swipee = userRepository.findById(request.getSwipeeId())
			.orElseThrow(() -> new RestApiException(ErrorCode.USER_NOT_FOUND));

		Swipe newSwipe = Swipe.builder()
			.swiper(swiper)
			.swipee(swipee)
			.type(request.getSwipeType())
			.build();

		swipeRepository.save(newSwipe);

	}
}
