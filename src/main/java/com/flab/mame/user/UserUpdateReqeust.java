package com.flab.mame.user;

import lombok.Getter;

@Getter
public class UserUpdateReqeust {

	private String password;

	private String nickname;

	private Integer age;

	private String gender;

	private String bio;

}
