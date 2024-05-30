package com.flab.mame.matcheduser;

import lombok.Getter;

@Getter
public class MatchingResponse {

	private Long id;

	private Long user1Id;

	private Long user2Id;

	public MatchingResponse(final Matching matching) {
		this.id = matching.getId();
		this.user1Id = matching.getMember1().getId();
		this.user2Id = matching.getMember2().getId();

	}
}
