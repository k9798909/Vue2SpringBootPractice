package com.example.SpringbootApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringbootApi.model.UserdataVo;
import com.example.SpringbootApi.res.BaseRes;
import com.example.SpringbootApi.res.LogindataRes;
import com.example.SpringbootApi.serivce.UserdataService;
import com.example.SpringbootApi.utility.JwtUtil;

@RestController
@RequestMapping("/user")
public class UserDataController {
	
	@Autowired
	private UserdataService userdataService;
	
	@GetMapping("/{id}")
	public BaseRes<LogindataRes> findBy(@PathVariable("id") Long id) {
		BaseRes<LogindataRes> res = new BaseRes<>();
		res.setData(userdataService.findBy(id));
		return res;
	}
	
	@GetMapping("/isLogin")
	public BaseRes<LogindataRes> isLogin(@AuthenticationPrincipal UserdataVo Userdata) {
		BaseRes<LogindataRes> res = new BaseRes<>();
		res.setData(userdataService.isLogin(Userdata));
		return res;
	}
	
	@GetMapping("/checkLoginByToken")
	public String checkToken(String token) {
		String username = JwtUtil.getUsernameFromToken(token);
		return SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null ? SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString() : "";
	}
	

}
