package com.flab.mame.photo;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/profile-images")
public class ProfileImageController {

	private final ProfileImageService photoService;

	@PostMapping
	public ProfileImage uploadPhoto(@RequestParam(value = "image") final MultipartFile image) throws IOException {
		return photoService.uploadImage(image);
	}

	@GetMapping(value = "/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> viewProfileImage(@PathVariable final Long id) throws IOException {
		byte[] bytes = photoService.viewProfileImage(id);

		return ResponseEntity
			.ok()
			.contentType(MediaType.IMAGE_JPEG)
			.body(bytes);
	}

	@PatchMapping("/{id}")
	public void updateProfileImage(@PathVariable final Long id, @RequestParam("image") final MultipartFile image) {
		photoService.updateProfileImage(id, image);
	}

	@DeleteMapping("/{id}")
	public void deleteProfileImage(@PathVariable final Long id) {
		photoService.deleteProfileImage(id);
	}
}
