package com.example.SpringbootApi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SpringbootApi.model.ProductVo;

public interface ProductDao extends JpaRepository<ProductVo, Long> {

}
