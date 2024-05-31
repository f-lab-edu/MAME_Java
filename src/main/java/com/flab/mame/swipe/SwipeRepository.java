package com.flab.mame.swipe;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flab.mame.profile.domain.Profile;

public interface SwipeRepository extends JpaRepository<Swipe, Long> {
	Optional<Swipe> findBySwiperAndSwipee(Profile swiper, Profile swipee);
}