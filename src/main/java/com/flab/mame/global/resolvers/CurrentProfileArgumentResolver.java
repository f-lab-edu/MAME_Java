package com.flab.mame.global.resolvers;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.flab.mame.global.exception.ErrorCode;
import com.flab.mame.global.exception.RestApiException;
import com.flab.mame.user.domain.UserSessionConst;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CurrentProfileArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(CurrentProfile.class);
	}

	@Override
	public Long resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {

		final HttpServletRequest request = (HttpServletRequest)webRequest.getNativeRequest();
		HttpSession session = request.getSession(false);

		if (session == null) {
			throw new RestApiException(ErrorCode.LOGIN_REQUIRED);
		}

		if (session.getAttribute(UserSessionConst.PROFILE_ID) == null) {
			throw new RestApiException(ErrorCode.PROFILE_INCOMPLETED);
		}

		Long profileId = (Long)session.getAttribute(UserSessionConst.PROFILE_ID);
		log.info("profileId = {}", profileId);
		return profileId;
	}
}
