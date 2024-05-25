package com.flab.mame.matcheduser;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flab.mame.user.domain.User;

public interface MatchedUserRepository extends JpaRepository<MatchedUser, Long> {

	List<MatchedUser> findAllByUser1(User user1);

	Optional<MatchedUser> findByUser1(User foundUser);
}
