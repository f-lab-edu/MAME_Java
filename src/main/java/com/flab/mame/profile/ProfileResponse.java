package com.flab.mame.profile;

import com.flab.mame.profile.domain.Profile;

import lombok.Getter;

@Getter
public class ProfileResponse {

	private Long id;

	private String nickname;

	private int age;

	private String introduction;

	public ProfileResponse(Profile profile) {
		this.id = profile.getId();
		this.nickname = profile.getNickname();
		this.age = profile.getAge();
		this.introduction = profile.getIntroduction();
	}
}
