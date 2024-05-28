package com.flab.mame.profile;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoApiResponse {

	private List<Address> documents;

	@Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Address {
		@JsonProperty("x")
		private Double longitude;

		@JsonProperty("y")
		private Double latitude;
	}
}
