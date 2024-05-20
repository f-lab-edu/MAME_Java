package com.flab.mame.profile;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class ProfileService {

	private final ProfileRepository profileRepository;

	public Profile createProfile(final ProfileCreateRequest request) {
		Profile newProfile = Profile.builder()
			.nickname(request.getNickname())
			.age(request.getAge())
			.gender(request.getGender())
			.introduction(request.getIntroduction())
			.build();

		return profileRepository.save(newProfile);

	}

	public Profile getProfileById(final Long id) {
		Profile foundProfile = profileRepository.findById(id).orElseThrow(() -> new RuntimeException("프로필 못찾음"));

		return foundProfile;
	}

	@Transactional
	public Profile updateProfile(final Long id, final ProfileUpdateRequest request) {
		Profile foundProfile = profileRepository.findById(id).orElseThrow(() -> new RuntimeException("프로필 못찾음"));

		foundProfile.updateProfile(request);

		return foundProfile;
	}
}
