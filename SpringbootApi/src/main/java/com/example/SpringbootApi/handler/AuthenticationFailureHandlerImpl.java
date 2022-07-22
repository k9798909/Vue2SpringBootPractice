package com.example.SpringbootApi.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.example.SpringbootApi.helper.ObjectWriterHelper;
import com.example.SpringbootApi.res.BaseRes;
import com.example.SpringbootApi.res.LogindataRes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		log.info("URL:{}",request.getRequestURL());
		log.info("AttributeNames:{}",request.getAttributeNames());
		response.setStatus(HttpStatus.OK.value());
		BaseRes<LogindataRes> res = new BaseRes<>(HttpStatus.FORBIDDEN.value(),exception.getMessage());
		res.setData(null);
		response.getOutputStream().print(ObjectWriterHelper.ObjectToJson(res));
	}

}
