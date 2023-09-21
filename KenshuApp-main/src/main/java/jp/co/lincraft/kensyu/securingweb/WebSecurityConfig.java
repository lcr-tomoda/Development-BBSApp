package jp.co.lincraft.kensyu.securingweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jp.co.lincraft.kensyu.service.UserService;

/**
 * ログインやユーザー権限を管理するクラス
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	/**
	 * ユーザーサービス
	 */
	@Autowired
	private UserService userService;

	/**
	 * アクセス権限を設定する
	 * @param http
	 * @return
	 * @throws Exception
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				// 許可するURL
				.authorizeHttpRequests((requests) -> requests.requestMatchers("/").permitAll()
						.requestMatchers("/user/input").permitAll() // ユーザー登録
						.requestMatchers("/user/confirm").permitAll() // ユーザー登録確認
						.requestMatchers("/user/complete").permitAll() // ユーザー登録完了
						.requestMatchers("/img/**").permitAll() // 画像
						.requestMatchers("/css/**").permitAll() // CSS
						.requestMatchers("/js/**").permitAll() // JS
						.anyRequest().authenticated())
				// ログイン情報の設定
				.formLogin((form) -> form.loginPage("/login").defaultSuccessUrl("/menu").permitAll())
				// ログアウトの設定
				.logout((logout) -> logout.permitAll());

		return http.build();
	}

	/**
	 * パスワードの暗号化方法を設定する
	 * @return
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
