package com.flab.mame.profileimage.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flab.mame.profileimage.domain.ProfileImage;

public interface ProfileImageRepository extends JpaRepository<ProfileImage, Long> {
}
