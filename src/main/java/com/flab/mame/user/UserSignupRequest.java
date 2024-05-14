package com.flab.mame.user;

import lombok.Getter;

@Getter
public class UserSignupRequest {

	private String email;

	private String password;

	private String nickname;

	private Integer age;

	private String gender;

}
