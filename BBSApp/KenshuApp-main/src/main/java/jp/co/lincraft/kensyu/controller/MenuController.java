package jp.co.lincraft.kensyu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * メニューコントローラ
 */
@Controller
public class MenuController {

	/**
	 * トップ画面
	 * @return
	 */
	@GetMapping(value = "/")
	public String index() {

		return "index";
	}
	
	/**
	 * メニュー画面
	 * @return
	 */
	@GetMapping(value = "/menu")
	public String menu() {

		return "menu";
	}
}