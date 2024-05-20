package com.flab.mame.profile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Profile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 10)
	@NotBlank
	private String nickname;

	@NotNull
	private int age;

	@Enumerated(EnumType.STRING)
	@NotBlank
	private Gender gender;

	@NotBlank
	private String introduction;

	public void updateProfile(final ProfileUpdateRequest request) {
		this.nickname = request.getNickname();
		this.age = request.getAge();
		this.gender = request.getGender();
		this.introduction = request.getIntroduction();
	}

	/*
	 * TODO: 유저와 연관관계 매핑
	 *
	 * */

}
