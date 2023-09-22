package jp.co.lincraft.kensyu.controller;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Conventions;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.lincraft.kensyu.entity.User;
import jp.co.lincraft.kensyu.service.UserService;

/**
 * ユーザーコントローラ
 */
@Controller
@SessionAttributes("user")
public class UserController {

	/**
	 * ユーザーサービス
	 */
	@Autowired
	UserService userService;


	/**
	 * ユーザー入力画面
	 * @param user ユーザー情報
	 * @param model viewに渡す用のモデル
	 * @return
	 */
	@GetMapping(value = "/user/input")
	public String input(User user, Model model) {
		
		
		if (!model.containsAttribute("user")) {
			model.addAttribute("user", new User());
		}
		
		return "user/input";
	}

	/**
	 * ユーザー入力確認画面
	 * @param user ユーザー情報
	 * @param bindingResult バリデーション用クラス
	 * @param redirectAttributes リダイレクト用クラス
	 * @return
	 */
	@PostMapping(value = "/user/confirm")
	public String confirm(@Validated User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		if (bindingResult.hasFieldErrors()) {
			redirectAttributes.addFlashAttribute(user);
			redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + Conventions.getVariableName(user),
					bindingResult);

			return "user/input";
		}

		return "user/confirm";
	}

	/**
	 * ユーザー入力完了画面
	 * @param user ユーザー情報
	 * @param sessionStatus セッション情報
	 * @return
	 */
	@PostMapping(value = "/user/complete")
	public String add(@Validated @ModelAttribute User user, SessionStatus sessionStatus) {

		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		userService.add(user);

		sessionStatus.setComplete();
		
		return "user/complete";
	}
}