package com.flab.mame.user;

import lombok.Getter;

@Getter
public class UserUpdateReqeust {
	/*
	 *
	 * TODO:Profile 엔티티를 별도로 만들어서 추후 삭제 예정.
	 *
	 * */
	private String password;

	private String nickname;

	private Integer age;

	private String gender;

	private String bio;

}
