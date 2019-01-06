package com.mindtree.guns.exception;

public class ServiceException extends Exception{

	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		System.out.println("Service Exception");
		return super.getMessage();
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ServiceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
}
