package com.flab.mame.profile;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flab.mame.global.annotation.CurrentUser;
import com.flab.mame.profile.domain.Profile;

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
	public void createProfile(@CurrentUser final Long userId, @RequestBody @Valid final ProfileCreateRequest request) {
		/*
		 * TODO 유저정보를 받아와서 프로필 완성 유무 체크
		 * */
		log.info("createProfile for userId: {}", userId);
		profileService.createProfile(userId, request);
	}

	// @GetMapping("/{profileId}")
	public Profile getProfileById(@PathVariable final Long profileId) {
		log.info("getProfileById with profileId: {}", profileId);
		Profile foundProfile = profileService.getProfileById(profileId);
		return foundProfile;
	}

	@PostMapping("/address")
	public void setProfileAddress(@CurrentUser final Long memberId, String address) {
		profileService.setProfileAddress(memberId, address);
	}

	@GetMapping
	public List<ProfileResponse> findNearByProfiles(@CurrentUser final Long memberId) {
		return profileService.findNearByProfiles(memberId);
	}

	@PutMapping
	public void updateProfile(@CurrentUser final Long userId,
		@RequestBody @Valid final ProfileUpdateRequest request) {
		log.info("updateProfile for user: {}", userId);

		profileService.updateProfile(userId, request);

	}

}
