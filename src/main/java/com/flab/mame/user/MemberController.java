package com.flab.mame.user;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flab.mame.global.annotation.CurrentMember;
import com.flab.mame.user.domain.Member;
import com.flab.mame.user.dto.MemberPasswordChangeRequest;
import com.flab.mame.user.dto.MemberSignupRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

	private final MemberService memberService;

	@PostMapping("/signup")
	public void signup(@RequestBody @Valid final MemberSignupRequest request) {
		memberService.signup(request);
	}

	@GetMapping
	public Member getUserById(@CurrentMember final Long memberId) {
		log.info("userId = {}", memberId);
		return memberService.getUserById(memberId);
	}

	@PatchMapping
	public void changePassword(@CurrentMember final Long memberId,
		@RequestBody @Valid MemberPasswordChangeRequest request) {
		log.info("memberId = {}", memberId);
		log.info("request = {}", request);
		memberService.changePassword(memberId, request);
	}

	@DeleteMapping
	public void deleteUser(@CurrentMember final Long id) {
		log.info("userId = {}", id);
		memberService.deleteUser(id);
	}

}
