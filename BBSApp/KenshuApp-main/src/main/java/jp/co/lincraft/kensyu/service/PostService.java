package jp.co.lincraft.kensyu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.lincraft.kensyu.entity.Post;
import jp.co.lincraft.kensyu.repository.PostRepository;

/**
 * 投稿サービス
 */
@Service
public class PostService {
	
	/**
	 * 投稿リポジトリ
	 */
	@Autowired
	PostRepository postRepository;

	/**
	 * 全件検索する
	 * @return
	 */
	public List<Post> searchAll() {

		return postRepository.findAll();
	}

	/**
	 * 投稿IDを条件に投稿情報を取得する
	 * @param id
	 * @return
	 */
	public Post findByID(long id) {

		return postRepository.findById(id).get();
	}

	/**
	 * 投稿情報を登録する
	 * @param post 投稿情報
	 * @return
	 */
	public Post add(Post post) {
		return postRepository.saveAndFlush(post);
	}
}