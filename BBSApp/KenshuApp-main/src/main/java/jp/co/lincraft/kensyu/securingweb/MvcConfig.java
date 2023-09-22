package jp.co.lincraft.kensyu.securingweb;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * MVCコンフィグ
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("home");
		registry.addViewController("/menu").setViewName("menu");
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/post").setViewName("post");
		registry.addViewController("/post/list").setViewName("post_list");
	}
}