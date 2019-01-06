package com.mindtree.guns.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.mindtree.guns.entity.Guns;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Gun Already Added")//404
public class AddGunException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public AddGunException(Guns gun){
		super("Gun Name Already present with name="+gun.getName());
	} 
}
