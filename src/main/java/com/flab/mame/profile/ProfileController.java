package com.flab.mame.profile;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flab.mame.global.annotation.CurrentMember;
import com.flab.mame.global.annotation.CurrentProfile;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/profiles")
public class ProfileController {

	private final ProfileService profileService;

	@PostMapping
	public void createProfile(@CurrentMember final Long userId,
		@RequestBody @Valid final ProfileCreateRequest request) {
		/*
		 * TODO 유저정보를 받아와서 프로필 완성 유무 체크
		 * */
		log.info("createProfile for userId: {}", userId);
		profileService.createProfile(userId, request);
	}

	// radius 1 = 1m, 100 = 100m
	@GetMapping
	public List<ProfileResponse> findProfilesNearBy(@CurrentProfile final Long profileId,
		@RequestParam(defaultValue = "10000", required = false) final Long radius) {
		log.info("profileId = {}", profileId);
		log.info("radius = {}", radius);
		return profileService.findProfilesNearBy(profileId, radius);
	}

	@PutMapping
	public void updateProfile(@CurrentProfile final Long profileId,
		@RequestBody @Valid final ProfileUpdateRequest request) {
		log.info("updateProfile for user: {}", profileId);

		profileService.updateProfile(profileId, request);

	}

}
