package com.flab.mame.profile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ProfileUpdateRequest {

	@NotBlank
	private String nickname;

	@NotNull
	private int age;

	@NotBlank
	private Gender gender;

	@NotBlank
	private String introduction;

}
