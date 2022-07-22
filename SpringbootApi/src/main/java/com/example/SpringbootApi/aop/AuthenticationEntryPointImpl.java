package com.example.SpringbootApi.aop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.example.SpringbootApi.helper.ObjectWriterHelper;
import com.example.SpringbootApi.res.BaseRes;
import com.example.SpringbootApi.res.LogindataRes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		log.error("AuthenticationEntryPoint Exception",authException);
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		BaseRes<LogindataRes>  res = new BaseRes<>(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.name());
		response.getOutputStream().println(ObjectWriterHelper.ObjectToJson(res));
	}

}
