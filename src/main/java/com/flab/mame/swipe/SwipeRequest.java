package com.flab.mame.swipe;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class SwipeRequest {

	@NotNull
	private Long swipeeId;

	@NotNull
	private SwipeType swipeType;
}
