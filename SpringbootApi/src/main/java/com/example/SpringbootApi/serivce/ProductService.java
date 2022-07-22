package com.example.SpringbootApi.serivce;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SpringbootApi.dao.ProductDao;
import com.example.SpringbootApi.model.ProductVo;

@Service
public class ProductService {
	
	@Autowired
	public ProductDao productDao;
	
	public List<ProductVo> findAll() {
		return productDao.findAll();
	}
	
	public ProductVo findBy(Long proid) {
		return productDao.findById(proid).get();
	}
	
	public void save(ProductVo product) {
		productDao.save(product);
	}
	
	public void delete(List<ProductVo> products) {
		products.stream().map(ProductVo::getProid).forEach(productDao::deleteById);
	}

}
