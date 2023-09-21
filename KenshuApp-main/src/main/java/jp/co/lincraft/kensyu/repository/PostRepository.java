package jp.co.lincraft.kensyu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.lincraft.kensyu.entity.Post;

/**
 * コメントレポジトリ
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}