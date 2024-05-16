package com.flab.mame.profile;

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
public class Profile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private int age;

	private String gender;

	private String bio;

	public void update(final ProfileUpdateRequest request) {
		this.name = request.getName();
		this.age = request.getAge();
		this.gender = request.getGender();
		this.bio = request.getBio();
	}

	/*
	 * TODO: 유저와 연관관계 매핑
	 *
	 * */

}
