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

	private String url = "https://dapi.kakao.com/v2/local/search/address.json?query=";

	public KakaoApiResponse.Address getLatitudeAndLongitudeFromAddress(final String address) {
		final RestTemplate restTemplate = new RestTemplate();

		String requestUrl = url + address;

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Authorization", KAKAO_REST_API_KEY);

		HttpEntity entity = new HttpEntity(httpHeaders);

		ResponseEntity<KakaoApiResponse> exchange = restTemplate.exchange(
			requestUrl,
			HttpMethod.GET,
			entity,
			KakaoApiResponse.class);
		/*
		 * TODO: Full adress를 받아서 filter로 한번 더 주소 검증한뒤 반환하기
		 *  stream().fiter(address ->
		 *
		 * */
		log.info("response = {}", exchange.getBody());
		/*
		 *
		 * TODO: 예외처리
		 * */
		KakaoApiResponse response = exchange.getBody();

		return response.getDocuments().get(0);
	}
}
