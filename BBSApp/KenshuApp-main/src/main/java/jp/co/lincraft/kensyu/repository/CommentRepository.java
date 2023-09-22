package jp.co.lincraft.kensyu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.lincraft.kensyu.entity.Comment;

/**
 * コメントレポジトリ
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}