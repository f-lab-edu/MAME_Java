package com.flab.mame.profile.domain;

import java.util.List;
import java.util.Optional;

import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
	Optional<Profile> findById(Long memberId);

	/*
	 *
	 * TODO: 추후 리팩토링 고민하기.
	 * */
	@Query(value = "select * from Profile p "
		+ "where ST_DWithin(p.location, :location, :radius, false) = true and p.id <> :id", nativeQuery = true)
	List<Profile> findProfilesNearBy(@Param("location") Point location,
		@Param("id") Long id,
		@Param("radius") Long radius);

}
