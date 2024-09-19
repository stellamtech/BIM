package com.tw.exception;

public class ItemAlreadyExistsException extends Exception {


	/**
	 * @author BILAL
	 */
	
	private static final long serialVersionUID = 1L;
	
	public ItemAlreadyExistsException(String msg) {
		super(msg);
	}
}
