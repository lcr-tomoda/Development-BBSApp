package jp.co.lincraft.kensyu.controller;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Conventions;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.lincraft.kensyu.entity.Comment;
import jp.co.lincraft.kensyu.entity.User;
import jp.co.lincraft.kensyu.service.CommentService;

/**
 * コメントコントローラ
 */
@Controller
public class CommentController {

	/**
	 * コメントサービス
	 */
	@Autowired
	CommentService commentService;

	/**
	 * コメント一覧画面を表示
	 * 
	 * @param model Model
	 * @return コメント一覧画面のHTML
	 */
	@GetMapping(value = "/comment/list")
	public String list(Model model) {

		List<Comment> commentList = commentService.searchAll();
		model.addAttribute("commentList", commentList);
		model.addAttribute("comment", new Comment());
		return "comment/list";
	}

	/**
	 * コメント追加
	 * @param user ログインユーザー情報
	 * @param comment コメント情報
	 * @param bindingResult バリデーション用クラス
	 * @param redirectAttributes リダイレクト用クラス
	 * @return
	 */
	@PostMapping(value = "/comment/add")
	public String add(
			@AuthenticationPrincipal User user,
			@Validated @ModelAttribute Comment comment, 
			BindingResult bindingResult, 
			RedirectAttributes redirectAttributes) {

		if (bindingResult.hasFieldErrors()) {
			redirectAttributes.addFlashAttribute(comment);
			redirectAttributes.addFlashAttribute(
					BindingResult.MODEL_KEY_PREFIX + Conventions.getVariableName(comment),
					bindingResult);

			return "redirect:/post/" + comment.getPostId() + "#formarea";
		}

		comment.setUserId(user.getId());
		comment.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		comment.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		commentService.add(comment);

		return "redirect:/post/" + comment.getPostId() + "#formarea";
	}
}