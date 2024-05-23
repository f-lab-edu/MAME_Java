package com.flab.mame.profileimage.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileImageRepository extends JpaRepository<ProfileImage, Long> {

	Optional<ProfileImage> findByIdAndProfileId(Long userId, Long profileImageId);
}
