package com.flab.mame.user;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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

	@Email(message = "올바른 이메일 형식이 아닙니다.")
	@NotBlank
	@Column(nullable = false, unique = true, length = 50)
	@Length(min = 1, max = 50, message = "이메일은 최소 1글자 이상, 최대 50글자 이하입니다.")
	private String email;

	@NotBlank
	@Column(nullable = false, length = 20)
	@Length(min = 12, max = 20, message = "비밀번호는 최소 12글자 이상, 최대 20글자 이하입니다.")
	private String password;


	/*
	 *  TODO: 프로필 정보 받아오기
	 *
	 * */


	/*
	 * TODO : List for photos
	 * */

}
