package jp.co.lincraft.kensyu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import jp.co.lincraft.kensyu.entity.Comment;
import jp.co.lincraft.kensyu.repository.CommentRepository;

/**
 * コメントサービス
 */
@Service
public class CommentService {
	
    /**
     * コメントリポジトリ
     */
    @Autowired
    CommentRepository commentRepository;
    
    public List<Comment> searchAll() {

    	return commentRepository.findAll();
    }

    /**
     * 投稿IDに紐づくコメントを取得する
     * @param postId
     * @return
     */
    public List<Comment> searchByPostId(String postId) {
    	
    	Comment comment = new Comment();
    	comment.setPostId(postId);

    	return commentRepository.findAll(Example.of(comment));
    }
    
    /**
     * コメントを登録する
     * @param comment
     */
    public void add(Comment comment) {
    	commentRepository.saveAndFlush(comment);
    }
}