package com.flab.mame.profile;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class KakaoMapsApi {
	
	@Value("${kakao.rest.api.key}")
	private String KAKAO_REST_API_KEY;

	public KakaoApiResponse.Address getLatitudeAndLongitudeFromAddress(final String address) {
		final RestTemplate restTemplate = new RestTemplate();
		String url = "https://dapi.kakao.com/v2/local/search/address.json?query=";
		String requestUrl = url + address;

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Authorization", KAKAO_REST_API_KEY);

		HttpEntity entity = new HttpEntity(httpHeaders);

		ResponseEntity<KakaoApiResponse> exchange = restTemplate.exchange(
			requestUrl,
			HttpMethod.GET,
			entity,
			KakaoApiResponse.class);

		log.info("response = {}", exchange.getBody());
		/*
		 *
		 * TODO: 예외처리
		 * */
		KakaoApiResponse response = exchange.getBody();
		return response.getDocuments().get(0);
	}
}
