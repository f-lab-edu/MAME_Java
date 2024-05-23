package com.flab.mame.profileimage.domain;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.flab.mame.profile.domain.Profile;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileImage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String imageURL;

	@NotBlank
	private String originalFileName;

	@NotBlank
	private String storedFileName;

	@NotNull
	private Long fileSize;

	@ManyToOne
	@JoinColumn(name = "profile_id")
	private Profile profile;

/*	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;*/

	public void updateProfileImage(final String IMAGE_BASE_URL, final MultipartFile image) throws IOException {
		this.originalFileName = image.getOriginalFilename();
		this.storedFileName = UUID.randomUUID() + "-" + image.getOriginalFilename();
		this.fileSize = image.getSize();
		this.imageURL = IMAGE_BASE_URL + storedFileName;
		image.transferTo(new File(IMAGE_BASE_URL + storedFileName));

	}

}
