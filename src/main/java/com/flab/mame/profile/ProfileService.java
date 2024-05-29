package com.flab.mame.profile;

import java.util.List;
import java.util.stream.Collectors;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	public void createProfile(final Long userId, final ProfileCreateRequest request) {
		if (profileRepository.findByMemberId(userId).isPresent()) {
			throw new RestApiException(ErrorCode.PROFILE_ALREADY_EXIST);
		}

		Member foundMember = memberRepository.findById(userId)
			.orElseThrow(() -> new RestApiException(ErrorCode.USER_NOT_FOUND));

		KakaoApiResponse.Address latitudeAndLongitudeFromAddress = kakaoMapsApi.getLatitudeAndLongitudeFromAddress(
			request.getAddress());

		final Double latitude = latitudeAndLongitudeFromAddress.getLatitude();
		final Double longitude = latitudeAndLongitudeFromAddress.getLongitude();

		Profile newProfile = Profile.builder()
			.nickname(request.getNickname())
			.age(request.getAge())
			.genderType(request.getGenderType())
			.introduction(request.getIntroduction())
			.member(foundMember)
			.location(addLocation(longitude, latitude))
			.build();

		profileRepository.save(newProfile);
	}

	/*
	 * TODO: 리팩토링 및 분리하는거 고민하기
	 * */
	private Point addLocation(Double longitude, Double latitude) {
		return new GeometryFactory().createPoint(new Coordinate(longitude, latitude));
	}

	@Transactional(readOnly = true)
	public Profile getProfileById(final Long id) {
		Profile foundProfile = profileRepository.findById(id)
			.orElseThrow(() -> new RestApiException(ErrorCode.PROFILE_NOT_FOUND));

		return foundProfile;
	}

	public void updateProfile(final Long userId, final ProfileUpdateRequest request) {
		final Profile foundProfile = profileRepository.findByMemberId(userId)
			.orElseThrow(() -> new RestApiException(ErrorCode.PROFILE_NOT_FOUND));

		foundProfile.updateProfile(request);
	}

	@Transactional(readOnly = true)
	public List<ProfileResponse> findProfilesNearBy(final Long memberId, final Long radius) {
		final Profile foundProfile = profileRepository.findByMemberId(memberId)
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
