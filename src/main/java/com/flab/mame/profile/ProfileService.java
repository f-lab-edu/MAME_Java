package com.flab.mame.profile;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flab.mame.global.exception.ErrorCode;
import com.flab.mame.global.exception.RestApiException;
import com.flab.mame.profile.domain.Profile;
import com.flab.mame.profile.domain.ProfileRepository;
import com.flab.mame.user.domain.User;
import com.flab.mame.user.domain.UserRepository;
import com.flab.mame.user.domain.UserSessionConst;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class ProfileService {

	private final ProfileRepository profileRepository;
	private final UserRepository userRepository;

	private final HttpSession httpSession;

	public void createProfile(final Long userId, final ProfileCreateRequest request) {
		User foundUser = userRepository.findById(userId)
			.orElseThrow(() -> new RestApiException(ErrorCode.USER_NOT_FOUND));

		Profile newProfile = Profile.builder()
			.nickname(request.getNickname())
			.age(request.getAge())
			.genderType(request.getGenderType())
			.introduction(request.getIntroduction())
			.user(foundUser)
			.build();

		profileRepository.save(newProfile);
		foundUser.createProfile(newProfile);

		if (httpSession.getAttribute(UserSessionConst.PROFILE_ID) == null) {
			httpSession.setAttribute(UserSessionConst.PROFILE_ID, newProfile.getId());
		}
	}

	@Transactional(readOnly = true)
	public Profile getProfileById(final Long id) {
		Profile foundProfile = profileRepository.findById(id)
			.orElseThrow(() -> new RestApiException(ErrorCode.PROFILE_NOT_FOUND));

		return foundProfile;
	}

	public Profile updateProfile(final Long userId, final ProfileUpdateRequest request) {
		User foundUser = userRepository.findById(userId)
			.orElseThrow(() -> new RestApiException(ErrorCode.USER_NOT_FOUND));

		foundUser.getProfile().updateProfile(request);

		return foundUser.getProfile();
	}
}
