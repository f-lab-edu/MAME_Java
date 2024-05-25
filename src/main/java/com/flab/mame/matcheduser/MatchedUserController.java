package com.flab.mame.matcheduser;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flab.mame.global.annotation.CurrentUser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user-matches")
public class MatchedUserController {

	private final MatchedUserService matchedUserService;

	@GetMapping
	public List<MatchedUserResponse> getAllUserMatchesByUser1Id(@CurrentUser final Long id) {
		log.info("User1Id = {}", id);
		return matchedUserService.getAllUserMatchesByUser1Id(id);
	}

	@DeleteMapping
	public void deleteUserMatchByUser1Id(@CurrentUser final Long id) {
		matchedUserService.deleteUserMatchByUser1Id(id);
	}
}
