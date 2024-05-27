package com.flab.mame.matcheduser;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flab.mame.user.domain.Member;

public interface MatchedUserRepository extends JpaRepository<MatchedUser, Long> {

	List<MatchedUser> findAllByMember1(Member member1);

	Optional<MatchedUser> findByMember1(Member foundMember);
}
