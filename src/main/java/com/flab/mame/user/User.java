package com.flab.mame.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String email;

	private String password;

	private String nickname;

	private Integer age;

	private String gender;

	private String bio;


	/*
	 * TODO : List for photos
	 * */

	public void update(UserUpdateReqeust reqeust) {
		this.nickname = reqeust.getNickname();
		this.age = reqeust.getAge();
		this.gender = reqeust.getGender();
		this.bio = reqeust.getBio();
	}

}
