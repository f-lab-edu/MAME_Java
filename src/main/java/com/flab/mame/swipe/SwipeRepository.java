package com.flab.mame.swipe;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flab.mame.user.domain.Member;

public interface SwipeRepository extends JpaRepository<Swipe, Long> {
	Optional<Swipe> findBySwiperAndSwipee(Member swipee, Member swiper);
}
