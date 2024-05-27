package com.flab.mame.matcheduser;

import lombok.Getter;

@Getter
public class MatchedUserResponse {

	private Long id;

	private Long user1Id;

	private Long user2Id;

	public MatchedUserResponse(final MatchedUser matchedUser) {
		this.id = matchedUser.getId();
		this.user1Id = matchedUser.getMember1().getId();
		this.user2Id = matchedUser.getMember2().getId();

	}
}
