package com.flab.mame.user.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Member, Long> {
	Optional<Member> findByEmail(String email);
}
