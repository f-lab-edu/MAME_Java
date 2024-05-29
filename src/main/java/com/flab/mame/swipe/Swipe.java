package com.flab.mame.swipe;

import com.flab.mame.user.domain.Member;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Swipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/*
	 * TODO: ENTITY PROFILE로 변경하기
	 * */
	@ManyToOne
	@JoinColumn(name = "swiper_member_id")
	private Member swiper;

	@ManyToOne
	@JoinColumn(name = "swipee_member_id")
	private Member swipee;

	@Enumerated(EnumType.STRING)
	private SwipeType type;

	/*
	 * TODO : 매칭 유무 Enum 구현하기
	 * */

}
