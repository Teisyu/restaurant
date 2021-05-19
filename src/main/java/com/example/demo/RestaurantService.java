package com.example.demo;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RestaurantService {

	/** 飲食店検索API リクエストURL */
	private static final String URL = "http://webservice.recruit.co.jp/hotpepper/gourmet/v1/?key=XXXXXX";

	public RestaurantDto service(String ido,String keido,String hankei){

		RestTemplate restTemplate = new RestTemplate();
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(URL);

		// 2. クエリパラメータを設定して文字列化する.
		String uri = builder.queryParam("lat", ido)
				.queryParam("key", "XXXXXX")
				.queryParam("lng", keido)
				.queryParam("range", hankei)
				.queryParam("format", "json")
				.toUriString();
		RequestEntity<Void> requestEntity =null;
		try {
			// 3. パラメータを設定したURLからRequestEntityを作成する.
			requestEntity = RequestEntity.get(new URI(uri)).build();
		}catch(URISyntaxException e){
			e.printStackTrace();
		}

		// 4. exchangeでAPIを呼び出す.
		ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

		RestaurantResultDto restaurantResultDto = new RestaurantResultDto();
		try {
			// JSON文字列をJavaオブジェクトに変換
			ObjectMapper mapper = new ObjectMapper();
			System.out.println(responseEntity.getBody());
			restaurantResultDto = mapper.readValue(responseEntity.getBody(), RestaurantResultDto.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(restaurantResultDto.getResults().getShop().get(0).getName());

		return restaurantResultDto.getResults();
	}

}