package com.flab.mame.photo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfileImageService {

	private String filePath = "/Users/dw/images/";

	private final ProfileImageRepository photoRepository;

	public ProfileImage uploadImage(MultipartFile image) throws IOException {

		String originalFileName = image.getOriginalFilename();
		String storedFileName = UUID.randomUUID() + "-" + originalFileName;
		image.transferTo(new File(filePath + storedFileName));

		ProfileImage newProfileImage = ProfileImage.builder()
			.filePath(filePath + storedFileName)
			.originalFileName(originalFileName)
			.storedFileName(storedFileName)
			.fileSize(image.getSize())
			.build();

		return photoRepository.save(newProfileImage);
	}

	public byte[] viewProfileImage(Long id) throws IOException {
		final ProfileImage foundProfileImage = photoRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("없어"));

		Path filePath = Paths.get(foundProfileImage.getFilePath());
		return Files.readAllBytes(filePath);
	}

	public void updateProfileImage(Long id, MultipartFile image) {
		ProfileImage foundProfileImage = photoRepository.findById(id).orElseThrow(() -> new RuntimeException("못찾음"));

		foundProfileImage.update(image);
	}

	public void deleteProfileImage(Long id) {
		ProfileImage foundProfileImage = photoRepository.findById(id).orElseThrow(() -> new RuntimeException("못찾음"));
		photoRepository.delete(foundProfileImage);
	}
}
