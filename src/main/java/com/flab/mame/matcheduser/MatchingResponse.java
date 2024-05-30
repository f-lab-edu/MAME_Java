package com.flab.mame.matcheduser;

import lombok.Getter;

@Getter
public class MatchingResponse {

	private Long id;

	private Long user1Id;

	private Long user2Id;

	public MatchingResponse(final Matching matching) {
		this.id = matching.getId();
		this.user1Id = matching.getProfile1().getId();
		this.user2Id = matching.getProfile2().getId();

	}
}
