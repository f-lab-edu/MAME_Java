package com.flab.mame.matcheduser;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flab.mame.user.domain.Member;

public interface MatchingRepository extends JpaRepository<Matching, Long> {

	List<Matching> findAllByMember1(Member member1);

	Optional<Matching> findByMember1(Member foundMember);
}
