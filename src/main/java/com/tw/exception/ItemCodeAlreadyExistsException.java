package com.tw.exception;

public class ItemCodeAlreadyExistsException extends Exception {


	/**
	 * @author BILAL
	 */
	
	private static final long serialVersionUID = 1L;
	
	public ItemCodeAlreadyExistsException(String msg) {
		super(msg);
	}
}
