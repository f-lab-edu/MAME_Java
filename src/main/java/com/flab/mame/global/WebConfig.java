package com.flab.mame.global;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.flab.mame.global.resolvers.CurrentProfileArgumentResolver;
import com.flab.mame.global.resolvers.CurrentUserArgumentResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginCheckInterceptor())
			.addPathPatterns("/**")
			.excludePathPatterns("/api/v1/login", "/api/v1/users/signup", "/error");
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(new CurrentUserArgumentResolver());
		resolvers.add(new CurrentProfileArgumentResolver());
	}
}
