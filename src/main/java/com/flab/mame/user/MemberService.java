package com.flab.mame.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flab.mame.global.exception.ErrorCode;
import com.flab.mame.global.exception.RestApiException;
import com.flab.mame.profile.domain.Profile;
import com.flab.mame.profile.domain.ProfileRepository;
import com.flab.mame.user.domain.Member;
import com.flab.mame.user.domain.MemberRepository;
import com.flab.mame.user.dto.MemberPasswordChangeRequest;
import com.flab.mame.user.dto.MemberSignupRequest;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;

	private final ProfileRepository profileRepository;

	public void signup(final MemberSignupRequest request) {

		if (memberRepository.findByEmail(request.getEmail()).isPresent()) {
			throw new RestApiException(ErrorCode.EMAIL_ALREADY_USED);
		}

		final Member newMember = Member.builder()
			.email(request.getEmail())
			.password(request.getPassword())
			.build();

		memberRepository.save(newMember);

	}

	public Member getUserById(final Long id) {
		/*
		 * TODO: 유저 못찾을 시 예외처리
		 * */
		final Member foundMember = memberRepository.findById(id)
			.orElseThrow(() -> new RestApiException(ErrorCode.MEMBER_NOT_FOUND));

		return foundMember;
	}

	/*public void updateUser(final Long id, UserUpdateReqeust reqeust) {
	 *//*
	 * TODO: 유저 못찾을 시 예외처리
	 * *//*
		User foundUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("유저 없음"));

		foundUser.update(reqeust);

	}*/

	public void deleteUser(final Long id) {
		/*
		 * TODO: 유저 못찾을 시 예외처리
		 * */
		final Member foundMember = memberRepository.findById(id)
			.orElseThrow(() -> new RestApiException(ErrorCode.MEMBER_NOT_FOUND));

		/*
		 * TODO: 양방향 매핑, 리팩토링 고민하기
		 *
		 * */
		Profile foundProfile = profileRepository.findById(foundMember.getId()).orElseThrow(
			() -> new RestApiException(ErrorCode.PROFILE_NOT_FOUND)
		);

		profileRepository.delete(foundProfile);
		memberRepository.delete(foundMember);

	}

	public void changePassword(final Long memberId, final MemberPasswordChangeRequest request) {
		final Member foundMember = memberRepository.findById(memberId)
			.orElseThrow(() -> new RestApiException(ErrorCode.MEMBER_NOT_FOUND));

		final String newPassword = request.getNewPassword();

		foundMember.changePassword(newPassword);
	}
}
