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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.lincraft.kensyu.entity.Comment;
import jp.co.lincraft.kensyu.entity.Post;
import jp.co.lincraft.kensyu.entity.User;
import jp.co.lincraft.kensyu.service.CommentService;
import jp.co.lincraft.kensyu.service.PostService;

/**
 * 投稿コントローラー
 */
@Controller
@SessionAttributes("post")
public class PostController {

	/**
	 * 投稿サービス
	 */
	@Autowired
	PostService postService;

	/**
	 * コメントサービス
	 */
	@Autowired
	CommentService commentService;

	/**
	 * 投稿一覧画面を表示
	 * 
	 * @param model viewに渡す用のモデル
	 * @return 投稿一覧画面のHTML
	 */
	@GetMapping(value = "/post/list")
	public String list(Model model) {

		List<Post> postList = postService.searchAll();
		model.addAttribute("postList", postList);
		return "post/list";
	}

	/**
	 * 投稿ページ
	 * @param user ログインユーザ情報
	 * @param id 投稿ID
	 * @param model viewに渡す用のモデル
	 * @return
	 */
	@GetMapping(value = "/post/{id}")
	public String post(@AuthenticationPrincipal User user, @PathVariable String id, Model model) {
		
		model.addAttribute("userId", user.getId());

		Post post = postService.findByID(Long.parseLong(id));
		model.addAttribute("post", post);

		List<Comment> comments = commentService.searchByPostId(id);
		model.addAttribute("commentList", comments);

		if (!model.containsAttribute("comment")) {
			model.addAttribute("comment", new Comment());
		}

		return "post/page";
	}
	
	/**
	 * 投稿入力画面
	 * @param post 投稿情報
	 * @param model viewに渡す用のモデル
	 * @return
	 */
	@GetMapping(value = "/post/input")
	public String input(Post post, Model model) {
		
		post = new Post();
		model.addAttribute("post", post);
		
		return "post/input";
	}
	
	/**
	 * 投稿確認画面
	 * @param post 投稿情報
	 * @param bindingResult バリデーション用クラス
	 * @param redirectAttributes リダイレクト用クラス
	 * @return
	 */
	@PostMapping(value = "/post/confirm")
	public String confirm(@Validated Post post, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		if (bindingResult.hasFieldErrors()) {
			redirectAttributes.addFlashAttribute(post);
			redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + Conventions.getVariableName(post),
					bindingResult);

			return "post/input";
		}

		return "post/confirm";
	}
	
	/**
	 * 投稿完了画面
	 * @param user ログインユーザ情報
	 * @param post 投稿情報
	 * @param sessionStatus セッション情報
	 * @param model viewに渡す用のモデル
	 * @return
	 */
	@PostMapping(value = "/post/complete")
	public String add(
			@AuthenticationPrincipal User user, 
			@Validated @ModelAttribute Post post, 
			SessionStatus sessionStatus, 
			Model model) {

		post.setUserId(user.getId());
		post.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		post.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		post = postService.add(post);

		sessionStatus.setComplete();
		model.addAttribute("post", new Post());
		
		
		return "redirect:/post/" + post.getId();
	}
	
}