package com.flab.mame.matcheduser;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flab.mame.global.annotation.CurrentMember;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user-matches")
public class MatchingController {

	private final MatchingService matchingService;

	@GetMapping
	public List<MatchingResponse> getAllUserMatchesByUser1Id(@CurrentMember final Long id) {
		log.info("User1Id = {}", id);
		return matchingService.getAllUserMatchesByUser1Id(id);
	}

	@DeleteMapping
	public void deleteUserMatchByUser1Id(@CurrentMember final Long id) {
		matchingService.deleteUserMatchByUser1Id(id);
	}
}
