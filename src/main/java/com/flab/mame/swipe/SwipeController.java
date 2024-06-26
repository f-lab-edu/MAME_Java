package com.flab.mame.swipe;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flab.mame.global.annotation.CurrentProfile;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/swipes")
public class SwipeController {

	private final SwipeService swipeService;

	@PostMapping
	public void swipeUser(@CurrentProfile final Long swiperProfileId, @RequestBody @Valid final SwipeRequest request) {
		/*
		 * TODO : 프로필이 완성되어야 스와이프 가능하도록?
		 * */
		log.info("swiperId = {}", swiperProfileId);
		log.info("swipeeId = {}", request.getSwipeeId());
		swipeService.swipeProfile(swiperProfileId, request);
	}
}
