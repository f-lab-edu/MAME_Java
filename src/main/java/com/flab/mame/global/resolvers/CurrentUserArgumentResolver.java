package com.flab.mame.global.resolvers;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.flab.mame.global.SessionConst;
import com.flab.mame.global.annotation.CurrentMember;
import com.flab.mame.global.exception.ErrorCode;
import com.flab.mame.global.exception.RestApiException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CurrentUserArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(CurrentMember.class);
	}

	@Override
	public Long resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {

		final HttpServletRequest request = (HttpServletRequest)webRequest.getNativeRequest();
		final HttpSession session = request.getSession(false);

		if (session == null) {
			throw new RestApiException(ErrorCode.LOGIN_REQUIRED);
		}

		final Long userId = (Long)session.getAttribute(SessionConst.USER_ID);

		log.info("userId = {}", userId);
		return userId;
	}
}
