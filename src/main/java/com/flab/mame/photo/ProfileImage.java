package com.flab.mame.photo;

import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

	private String filePath;

	private String originalFileName;

	private String storedFileName;

	private Long fileSize;

	public void update(MultipartFile multipartFile) {
		this.originalFileName = multipartFile.getOriginalFilename();
		this.storedFileName = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();
		this.fileSize = multipartFile.getSize();
		this.filePath = "/Users/dw/images/" + storedFileName;
	}

	/*
	 * TODO: 유저랑 연관관계 매핑
	 *
	 * */
}
