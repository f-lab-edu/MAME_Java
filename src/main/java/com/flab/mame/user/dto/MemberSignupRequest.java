package com.flab.mame.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class MemberSignupRequest {

	@Email(message = "올바르지 않은 이메일 형식입니다.")
	@NotBlank(message = "이메일은 필수 값 입니다.")
	private String email;

	@NotBlank(message = "비밀번호는 필수 값 입니다.")
	private String password;

}
