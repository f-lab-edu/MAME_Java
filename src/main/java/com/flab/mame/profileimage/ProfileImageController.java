package com.flab.mame.profileimage;

import java.io.IOException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.flab.mame.global.annotation.CurrentMember;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/profile-images")
public class ProfileImageController {

	private final ProfileImageService photoService;

	@PostMapping
	public void addProfileImage(@CurrentMember final Long userId,
		@RequestParam(value = "image") final MultipartFile image) throws IOException {
		log.info("addProfileImage with userId = {}", userId);
		photoService.addProfileImage(userId, image);
	}

	@PatchMapping("/{profileImageId}")
	public void changeProfileImage(@CurrentMember final Long userId,
		@PathVariable final Long profileImageId,
		@RequestParam("image") final MultipartFile image) throws IOException {
		photoService.changeProfileImage(userId, profileImageId, image);
	}

	@DeleteMapping("/{profileImageId}")
	public void deleteProfileImage(@CurrentMember final Long userId, @PathVariable final Long profileImageId) {
		photoService.deleteProfileImage(userId, profileImageId);
	}
}
