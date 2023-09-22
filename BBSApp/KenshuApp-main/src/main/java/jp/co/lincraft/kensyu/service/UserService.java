package jp.co.lincraft.kensyu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jp.co.lincraft.kensyu.entity.User;
import jp.co.lincraft.kensyu.repository.UserRepository;

/**
 * ユーザーサービス
 */
@Service
public class UserService implements UserDetailsService {
	
    /**
     * ユーザーリポジトリ
     */
	@Autowired
    private final UserRepository userRepository;
    
    /**
     * コンストラクタ
     * @param userRepository
     */
    public UserService(UserRepository userRepository) {
    	this.userRepository = userRepository;
    }

	/**
	 * ログインIDからユーザー情報（ログイン用）を取得する
	 * 
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return searchByEmail(username);
	}
    
    /**
     * ユーザー情報を全件取得する
     * @return
     */
    public List<User> searchAll() {

    	return userRepository.findAll();
    }
    
    /**
     * emailから一意のユーザー情報を取得する
     * @param email
     * @return
     */
    public User searchByEmail(String email) {

    	User user = new User();
    	user.setEmail(email);
    	
    	return userRepository.findOne(Example.of(user)).get();
    }

    /**
     * ユーザー情報を追加する
     * @param user
     */
    public void add(User user) {
    	userRepository.saveAndFlush(user);
    }
}