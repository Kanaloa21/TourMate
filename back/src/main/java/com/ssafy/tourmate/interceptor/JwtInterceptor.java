package com.ssafy.tourmate.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ssafy.tourmate.exception.UnAuthorizedException;
import com.ssafy.tourmate.service.JwtService;

@Component
public class JwtInterceptor implements HandlerInterceptor {

	public static final Logger logger = LoggerFactory.getLogger(JwtInterceptor.class);

	private static final String HEADER_AUTH = "access-token";

	@Autowired
	private JwtService jwtService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("관리자 토큰 인터셉터 발생");
		final String token = request.getHeader(HEADER_AUTH);
		
		if (token != null && jwtService.checkToken(token) && ((Integer) jwtService.get(token).get("authorizeLevel")) >= 2) {
			logger.info("관리자 권한 사용 가능 : {}", token);
			return true;
		} else {
			logger.info("관리자 권한 사용 불가능 : {}", token);
			throw new UnAuthorizedException();
		}

	}

}
