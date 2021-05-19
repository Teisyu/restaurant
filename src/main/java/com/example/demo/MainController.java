package com.example.demo;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

	@Autowired
	RestaurantService resService;

	/**
	 * 飲食店入力フォーム
	 * @return "resutaurant"
	 */
	@RequestMapping("/restaurant")
	public String restaurantForm(HttpSession session, Model model) {
		return "restaurant";
	}

	/**
	 * 飲食店情報表示	
	 * @return "restaurant-confirm"
	 */
	@RequestMapping(value="/restaurant-confirm", method=RequestMethod.POST)
	public String restaurantConfirm(HttpSession session, Model model, 

			@RequestParam
			(name = "keido") String keido,
			@RequestParam
			(name = "ido") String ido,
			@RequestParam
			(name = "hankei") String hankei){

		// 一応必須チェックのみ 数字・桁数チェックは省略
		// nullまたは空文字の場合、入力フォームにエラーメッセージを表示
		if (keido == null || keido.equals("")) {
			model.addAttribute("errorMessage", "検索値を入力してください。");
			return restaurantForm(session, model);
		}

		// 飲食店検索APIサービス呼び出し
		RestaurantDto restaurantDto = resService.service(ido,keido,hankei);		
		// thymeleafでリストを展開して表示する
		model.addAttribute("restaurantList", restaurantDto.getShop());

		return "restaurant-confirm";	
	}
}