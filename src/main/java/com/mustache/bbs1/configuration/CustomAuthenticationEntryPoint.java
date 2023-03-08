package com.mustache.bbs1.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mustache.bbs1.domain.Response;
import com.mustache.bbs1.exception.ErrorCode;
import com.mustache.bbs1.exception.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
		log.info("인증 실패");

		ObjectMapper objectMapper = new ObjectMapper();
		SecurityCustomException securityCustomException = new SecurityCustomException(ErrorCode.INVALID_PERMISSION);
		ErrorResponse errorResponse = new ErrorResponse(securityCustomException.getErrorCode(), securityCustomException.getErrorCode().getMessage());
		Response<ErrorResponse> error = Response.error(errorResponse);

		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(objectMapper.writeValueAsString(error)); //Response객체를 response의 바디값으로 파싱
	}

}
