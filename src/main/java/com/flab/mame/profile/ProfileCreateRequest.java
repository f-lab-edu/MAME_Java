package com.flab.mame.profile;

import com.flab.mame.profile.domain.GenderType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ProfileCreateRequest {

	@NotBlank
	private String nickname;

	@NotNull
	private int age;

	/*
	 * TODO: enum validation
	 *
	 * */
	@NotNull
	private GenderType genderType;

	@NotBlank
	private String introduction;
}
