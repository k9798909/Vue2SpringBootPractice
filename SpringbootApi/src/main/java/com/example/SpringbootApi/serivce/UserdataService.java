package com.example.SpringbootApi.serivce;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.SpringbootApi.dao.UserdataDao;
import com.example.SpringbootApi.model.UserdataVo;
import com.example.SpringbootApi.res.LogindataRes;

@Service
public class UserdataService {
	
	@Autowired
	public UserdataDao userdataDao;
	@Autowired
	public PasswordEncoder passwordEncoder;

	public void save() {
		UserdataVo userdata = new UserdataVo();
		userdata.setUsername("123456");
		userdata.setPassword(passwordEncoder.encode("123456"));
		userdata.setIdno("H123456789");
		userdata.setName("王曉明");
		userdataDao.save(userdata);
	}
	
	public LogindataRes findBy(Long id) {
		Optional<UserdataVo> vo = userdataDao.findById(id);
		LogindataRes userdata = new LogindataRes();
		userdata.setId(vo.get().getId());
		userdata.setName(vo.get().getName());
		userdata.setUsername(vo.get().getUsername());
		return userdata;
	}
	
	public LogindataRes isLogin(UserdataVo vo) {
		if (vo != null) {
			LogindataRes userdata = new LogindataRes();
			userdata.setId(vo.getId());
			userdata.setName(vo.getName());
			userdata.setUsername(vo.getUsername());
			return userdata;
		} else {
			return null;
		}
	}
	


}
