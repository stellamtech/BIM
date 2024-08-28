package com.tw.generics;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErrorResponse {

	/**
	 * @author BILAL
	 */
	private static final String BAD_REQUEST = "BAD REQUEST";
	private static final String NOT_FOUND = "NOT FOUND";
	private static final String CONFLICT = "CONFLICT";
	private static final String BAD_GATEWAY = "BAD GATEWAY";
	private static final String INTERNAL_SERVER_ERROR = "INTERNAL SERVER ERROR";
	
	private String statusText;
	private int code;
	private String message;
	private Object data;
	
	public static ResponseEntity<?> build(int code) {
		ErrorResponse r = new ErrorResponse();
		r.code = code;
		r.message = getMessage(code);
		r.statusText = getStatusText(code);
		return ResponseEntity.status(HttpStatus.valueOf(code)).body(r);
	}
	
	public static ResponseEntity<?> build(int code, String message) {
		ErrorResponse r = new ErrorResponse();
		r.code = code;
		r.message = message;
		r.statusText = getStatusText(code);
		return ResponseEntity.status(HttpStatus.valueOf(code)).body(r);
	}
	
	public static ResponseEntity<?> build(int code, String message, Object data) {
		ErrorResponse r = new ErrorResponse();
		r.code = code;
		r.message = message;
		r.data = data;
		r.statusText = getStatusText(code);
		return ResponseEntity.status(HttpStatus.valueOf(code)).body(r);
	}
	
	private static String getStatusText (int code) {
		String statusText = null;
		switch (code ) {
			
			case Code.BAD_REQUEST :
				statusText = BAD_REQUEST;
				break;
			case Code.NOT_FOUND : 
				statusText = NOT_FOUND;
				break;
			case Code.CONFLICT : 
				statusText = CONFLICT;
				break;
			case Code.BAD_GATEWAY : 
				statusText = BAD_GATEWAY;
				break;
			case Code.INTERNAL_SERVER_ERROR : 
				statusText = INTERNAL_SERVER_ERROR;
				break;
			
		}
		return statusText;
	}
	
	private static String getMessage (int code) {
		String statusMessage = null;
		switch (code ) {
			
			case Code.BAD_REQUEST : 
				statusMessage = Messages.OK;
				break;
			case Code.NOT_FOUND : 
				statusMessage = NOT_FOUND;
				break;
			case Code.CONFLICT : 
				statusMessage = CONFLICT;
				break;
			case Code.BAD_GATEWAY : 
				statusMessage = BAD_GATEWAY;
				break;
			case Code.INTERNAL_SERVER_ERROR : 
				statusMessage = Messages.INTERNAL_SERVER_ERROR;
				break;
		}
		return statusMessage;
	}

	public String getStatusText() {
		return statusText;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public Object getData() {
		return data;
	}
}
