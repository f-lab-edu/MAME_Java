package com.flab.mame.profile;

import java.util.List;
import java.util.stream.Collectors;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flab.mame.global.SessionConst;
import com.flab.mame.global.exception.ErrorCode;
import com.flab.mame.global.exception.RestApiException;
import com.flab.mame.profile.domain.Profile;
import com.flab.mame.profile.domain.ProfileRepository;
import com.flab.mame.user.domain.Member;
import com.flab.mame.user.domain.MemberRepository;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ProfileService {

	private final ProfileRepository profileRepository;
	private final MemberRepository memberRepository;
	private final HttpSession httpSession;
	private final KakaoMapsApi kakaoMapsApi;

	public void createProfile(final Long memberId, final ProfileCreateRequest request) {
		if (profileRepository.findById(memberId).isPresent()) {
			throw new RestApiException(ErrorCode.PROFILE_ALREADY_EXIST);
		}

		final Member foundMember = memberRepository.findById(memberId)
			.orElseThrow(() -> new RestApiException(ErrorCode.MEMBER_NOT_FOUND));

		final KakaoApiResponse.Address latitudeAndLongitudeFromAddress = kakaoMapsApi.getLatitudeAndLongitudeFromAddress(
			request.getAddress());

		final Double latitude = latitudeAndLongitudeFromAddress.getLatitude();
		final Double longitude = latitudeAndLongitudeFromAddress.getLongitude();

		final Profile newProfile = Profile.builder()
			.nickname(request.getNickname())
			.age(request.getAge())
			.genderType(request.getGenderType())
			.introduction(request.getIntroduction())
			.location(addLocation(longitude, latitude))
			.member(foundMember)
			.build();
		/*
		 * TODO : 1:1 양방향은 수동으로 넣어줘야하는데 고민해보기, Persis전에 세션을 저장할수있는 방법?
		 *
		 * */
		foundMember.addProfile(newProfile);
		httpSession.setAttribute(SessionConst.PROFILE_ID, newProfile.getId());
		profileRepository.save(newProfile);

		log.info("foundMember.getProfile() = {}", foundMember.getProfile());
		log.info("newProfile = {}", newProfile);
		log.info("profileId = {}", newProfile.getId());

	}

	/*
	 * TODO: 리팩토링 및 분리하는거 고민하기
	 * */
	private Point addLocation(Double longitude, Double latitude) {
		return new GeometryFactory().createPoint(new Coordinate(longitude, latitude));
	}

	public void updateProfile(final Long userId, final ProfileUpdateRequest request) {
		final Profile foundProfile = profileRepository.findById(userId)
			.orElseThrow(() -> new RestApiException(ErrorCode.PROFILE_NOT_FOUND));

		foundProfile.updateProfile(request);
	}

	@Transactional(readOnly = true)
	public List<ProfileResponse> findProfilesNearBy(final Long profileId, final Long radius) {
		final Profile foundProfile = profileRepository.findById(profileId)
			.orElseThrow(() -> new RestApiException(ErrorCode.PROFILE_NOT_FOUND));

		final Point location = foundProfile.getLocation();

		final List<Profile> nearbyProfiles = profileRepository.findProfilesNearBy(location, foundProfile.getId(),
			radius);
		log.info("nearbyProfiles Size = {}", nearbyProfiles.size());

		return nearbyProfiles.stream()
			.map(ProfileResponse::new)
			.collect(Collectors.toList());
	}
}
