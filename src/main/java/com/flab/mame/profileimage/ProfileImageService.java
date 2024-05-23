package com.flab.mame.profileimage;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.flab.mame.global.exception.ErrorCode;
import com.flab.mame.global.exception.RestApiException;
import com.flab.mame.profile.domain.Profile;
import com.flab.mame.profile.domain.ProfileRepository;
import com.flab.mame.profileimage.domain.ProfileImage;
import com.flab.mame.profileimage.domain.ProfileImageRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ProfileImageService {

	@Value("${image.url}")
	private String IMAGE_BASE_URL;

	private final ProfileRepository profileRepository;
	private final ProfileImageRepository profileImageRepository;

	public void addProfileImage(final Long userId, final MultipartFile image) throws IOException {
		final Profile foundProfile = profileRepository.findByUserId(userId)
			.orElseThrow(() -> new RestApiException(ErrorCode.PROFILE_NOT_FOUND));
		/*
		 * TODO : 추후 Util 만들기
		 * */
		String originalFileName = image.getOriginalFilename();
		String storedFileName = UUID.randomUUID() + "-" + originalFileName;
		image.transferTo(new File(IMAGE_BASE_URL + storedFileName));

		final ProfileImage newProfileImage = ProfileImage.builder()
			.imageURL(IMAGE_BASE_URL + storedFileName)
			.originalFileName(originalFileName)
			.storedFileName(storedFileName)
			.fileSize(image.getSize())
			.profile(foundProfile)
			.build();

		profileImageRepository.save(newProfileImage);
		foundProfile.addProfileImage(newProfileImage);

	}

	@Transactional(readOnly = true)
	public ProfileImage viewProfileImage(final Long id) {
		final ProfileImage foundProfileImage = profileImageRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("없어"));

		return foundProfileImage;
	}

	public void changeProfileImage(final Long userId, final Long profileImageId,
		final MultipartFile image) throws
		IOException {
		/*
		 * TODO: Checked Exception handling needed
		 * */
		final Profile profile = profileRepository.findByUserId(userId)
			.orElseThrow(() -> new RestApiException(ErrorCode.PROFILE_NOT_FOUND));

		final ProfileImage foundProfileImage = profileImageRepository.findByIdAndProfileId(profileImageId,
				profile.getId())
			.orElseThrow(() -> new RestApiException(ErrorCode.PROFILE_IMAGE_NOT_FOUND));

		foundProfileImage.updateProfileImage(IMAGE_BASE_URL, image);
	}

	public void deleteProfileImage(final Long userId, final Long profileImageId) {
		final Profile profile = profileRepository.findByUserId(userId)
			.orElseThrow(() -> new RestApiException(ErrorCode.PROFILE_NOT_FOUND));

		ProfileImage foundProfileImage = profileImageRepository.findByIdAndProfileId(profileImageId, profile.getId())
			.orElseThrow(() -> new RestApiException(ErrorCode.PROFILE_IMAGE_NOT_FOUND));

		profileImageRepository.delete(foundProfileImage);
	}
}
