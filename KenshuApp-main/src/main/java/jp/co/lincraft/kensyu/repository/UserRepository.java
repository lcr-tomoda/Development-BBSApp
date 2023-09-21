package jp.co.lincraft.kensyu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.lincraft.kensyu.entity.User;

/**
 * コメントレポジトリ
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
}