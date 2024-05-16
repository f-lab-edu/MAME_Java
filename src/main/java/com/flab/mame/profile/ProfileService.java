package com.flab.mame.profile;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProfileService {

	private final ProfileRepository profileRepository;

	public Profile createProfile(final ProfileCreateRequest request) {
		Profile newProfile = Profile.builder()
			.name(request.getName())
			.age(request.getAge())
			.gender(request.getGender())
			.bio(request.getBio())
			.build();

		return profileRepository.save(newProfile);

	}

	public Profile findById(final Long id) {
		Profile foundProfile = profileRepository.findById(id).orElseThrow(() -> new RuntimeException("프로필 못찾음"));

		return foundProfile;
	}

	@Transactional
	public Profile updateProfile(final Long id, final ProfileUpdateRequest request) {
		Profile foundProfile = profileRepository.findById(id).orElseThrow(() -> new RuntimeException("프로필 못찾음"));

		foundProfile.update(request);

		return foundProfile;
	}
}
