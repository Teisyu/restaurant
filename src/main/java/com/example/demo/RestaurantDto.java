package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RestaurantDto {

	/** 飲食店情報リスト */
	List<RestaurantDataDto> shop = new ArrayList<>();

	public List<RestaurantDataDto> getShop() {
		return shop;
	}

	public void setShop(List<RestaurantDataDto> shop) {
		this.shop = shop;
	}




}