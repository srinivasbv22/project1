package com.mindtree.guns.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Gun Not Found")//404
public class GunNotFoundException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public GunNotFoundException(String name){
		super("GunNotFoundException with id="+name);
	} 

}
