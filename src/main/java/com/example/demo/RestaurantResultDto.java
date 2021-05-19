package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RestaurantResultDto {
	
	/** 飲食店情報リスト */
	RestaurantDto results;

	public RestaurantDto getResults() {
		return results;
	}

	public void setResults(RestaurantDto results) {
		this.results = results;
	}

}