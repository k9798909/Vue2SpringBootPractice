package com.example.SpringbootApi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="product")
@Data
@NoArgsConstructor
public class ProductVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "proid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long proid;
	
	@Column(name="proname")
	private String proname;
	
	@Column(name="proprice")
	private Integer proprice;
	
	@Column(name="proqty")
	private Integer proqty;
}
