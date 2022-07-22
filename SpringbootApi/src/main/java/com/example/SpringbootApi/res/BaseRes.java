package com.example.SpringbootApi.res;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class BaseRes<T> {
	
	private Integer status;
	private String message;
	private T data;
	
    public BaseRes() {
        this.status = HttpStatus.OK.value();
        this.message = "success";
    }
    
    public BaseRes(String message) {
    	this.message = message;
    }
	
    public BaseRes(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

}
