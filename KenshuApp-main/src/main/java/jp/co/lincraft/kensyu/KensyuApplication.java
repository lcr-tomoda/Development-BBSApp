package jp.co.lincraft.kensyu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * spring boot 起動クラス
 */
@SpringBootApplication
public class KensyuApplication {

	/**
	 * メインメソッド
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(KensyuApplication.class, args);
	}

}
