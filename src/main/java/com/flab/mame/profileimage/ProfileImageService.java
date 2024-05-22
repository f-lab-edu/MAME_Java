package com.flab.mame.profileimage;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.flab.mame.profileimage.domain.ProfileImage;
import com.flab.mame.profileimage.domain.ProfileImageRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfileImageService {

	@Value("${image.url}")
	private String IMAGE_BASE_URL;

	private final ProfileImageRepository photoRepository;

	public ProfileImage uploadImage(MultipartFile image) throws IOException {

		String originalFileName = image.getOriginalFilename();
		String storedFileName = UUID.randomUUID() + "-" + originalFileName;
		image.transferTo(new File(IMAGE_BASE_URL + storedFileName));

		ProfileImage newProfileImage = ProfileImage.builder()
			.imageURL(IMAGE_BASE_URL + storedFileName)
			.originalFileName(originalFileName)
			.storedFileName(storedFileName)
			.fileSize(image.getSize())
			.build();

		return photoRepository.save(newProfileImage);
	}

	public ProfileImage viewProfileImage(Long id) throws IOException {
		final ProfileImage foundProfileImage = photoRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("없어"));

		return foundProfileImage;
	}

	public ProfileImage updateProfileImage(Long id, MultipartFile image) {
		ProfileImage foundProfileImage = photoRepository.findById(id).orElseThrow(() -> new RuntimeException("못찾음"));

		foundProfileImage.updateProfileImage(IMAGE_BASE_URL, image);

		return foundProfileImage;
	}

	public void deleteProfileImage(Long id) {
		ProfileImage foundProfileImage = photoRepository.findById(id).orElseThrow(() -> new RuntimeException("못찾음"));
		photoRepository.delete(foundProfileImage);
	}
}
