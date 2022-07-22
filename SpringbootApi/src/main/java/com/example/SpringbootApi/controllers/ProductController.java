package com.example.SpringbootApi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringbootApi.model.ProductVo;
import com.example.SpringbootApi.serivce.ProductService;

@RestController()
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	
	@GetMapping
	public ResponseEntity<List<ProductVo>> findAll() {
		return ResponseEntity.ok(productService.findAll());
	}
	
	@GetMapping("/{proid}")
	public ResponseEntity<ProductVo> findBy(@PathVariable("proid") Long proid) {
		return ResponseEntity.ok(productService.findBy(proid));
	}
	
	@PostMapping("/insert")
	public void insert(@RequestBody ProductVo product) {
		productService.save(product);
	}
	
	@PutMapping("/update")
	public void update(@RequestBody ProductVo product) {
		productService.save(product);
	}
	
	@DeleteMapping("/delete")
	public void update(@RequestBody List<ProductVo> products) {
		productService.delete(products);
	}

//	@Autowired
//	private LocalDateTime startTime;
//	@Value("classpath:static/test.PNG")
//	private Resource resource;

//	@GetMapping("/{id}")
//	public ResponseEntity<String> getPathVal(@PathVariable("id") String id) {
//		try (InputStream is = resource.getInputStream();
//				BufferedInputStream bis = new BufferedInputStream(is);
//				OutputStream os = new FileOutputStream("C:\\Users\\2008020\\Desktop\\testtest.png"); 
//				BufferedOutputStream bos = new BufferedOutputStream(os);) {
//			byte[] buffer = new byte[1024];
//			while (bis.read(buffer) != -1) {
//				bos.write(buffer);
//			}
//			bos.flush();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return ResponseEntity.ok(id + startTime);
//	}

}
