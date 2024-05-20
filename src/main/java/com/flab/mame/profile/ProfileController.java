package com.flab.mame.profile;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Profile> createProfile(@RequestBody @Valid final ProfileCreateRequest request) {
		/*
		 * TODO 유저정보를 받아와서 프로필 완성 유무 체크
		 * */

		Profile profile = profileService.createProfile(request);

		return ResponseEntity.ok(profile);
	}

	@GetMapping("/{id}")
	public Profile getProfileById(@PathVariable final Long id) {
		Profile foundProfile = profileService.getProfileById(id);

		return foundProfile;
	}

	@PutMapping("/{id}")
	public Profile updateProfile(@PathVariable final Long id, @RequestBody @Valid final ProfileUpdateRequest request) {
		return profileService.updateProfile(id, request);
	}

}
