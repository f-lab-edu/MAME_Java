package com.flab.mame.profile.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
	Optional<Profile> findByMemberId(Long memberId);
}
