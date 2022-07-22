package com.example.SpringbootApi.serivce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.SpringbootApi.dao.UserdataDao;
import com.example.SpringbootApi.model.UserdataVo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	public UserdataDao userdataDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserdataVo userdata = userdataDao.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("查無使用者帳號資料"));
		return userdata;
	}

}
