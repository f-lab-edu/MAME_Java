package com.flab.mame.profile.domain;

import com.flab.mame.profile.ProfileUpdateRequest;
import com.flab.mame.user.domain.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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

	@Column(nullable = false)
	@NotNull
	private int age;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	@NotNull
	private GenderType genderType;

	@Column(nullable = false)
	@NotBlank
	private String introduction;

	/*
	 * TODO: 유저와 연관관계 매핑
	 *
	 * */
	@OneToOne(mappedBy = "profile")
	private User user;

	public void updateProfile(final ProfileUpdateRequest request) {
		this.nickname = request.getNickname();
		this.age = request.getAge();
		this.genderType = request.getGenderType();
		this.introduction = request.getIntroduction();
	}

}
