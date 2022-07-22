package com.example.SpringbootApi.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.example.SpringbootApi.helper.ObjectWriterHelper;
import com.example.SpringbootApi.model.UserdataVo;
import com.example.SpringbootApi.res.BaseRes;
import com.example.SpringbootApi.res.LogindataRes;
import com.example.SpringbootApi.utility.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		log.info("URL:{}",request.getRequestURL());
		log.info("AttributeNames:{}",request.getAttributeNames());
		
		UserdataVo vo = (UserdataVo) authentication.getPrincipal();
		String token = JwtUtil.generateToken(authentication.getName());
		
		LogindataRes UserdataRes = new LogindataRes();
		UserdataRes.setName(vo.getName());
		UserdataRes.setUsername(vo.getUsername());
		UserdataRes.setId(vo.getId());
		UserdataRes.setToken(token);
		
		
		BaseRes<LogindataRes> res = new BaseRes<>();
		res.setData(UserdataRes);
		
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.write(ObjectWriterHelper.ObjectToJson(UserdataRes));
	}

}
