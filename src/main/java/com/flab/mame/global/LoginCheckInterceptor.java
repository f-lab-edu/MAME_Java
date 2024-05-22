package com.flab.mame.global;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.flab.mame.user.domain.UserSessionConst;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
		Exception {

		log.info("URI {}", request.getRequestURI());
		log.info("Session {}", request.getSession().getId());
		HttpSession session = request.getSession();

		if (session == null || session.getAttribute(UserSessionConst.USER_ID) == null) {
			throw new RestApiException(ErrorCode.LOGIN_REQUIRED);
		}

		return true;
	}
}
