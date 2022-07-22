package com.example.SpringbootApi.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SpringbootApi.model.UserdataVo;

public interface UserdataDao extends JpaRepository<UserdataVo, Long> {
	
	Optional<UserdataVo> findByUsername(String account);

}
