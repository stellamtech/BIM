package com.tw.generics;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Response {
	 
	/**
	 * @author BILAL
	 */
	
	private static final String OK = "OK";
	private static final String CREATED = "CREATED";
	private static final String ACCEPTED = "ACCEPTED";
	
	private String statusText;
	private int code;
	private String message;
	private Object data;
	
	
	public static Response of(int code) {
		Response r = new Response();
		r.code = code;
		r.message = getMessage(code);
		r.statusText = getStatusText(code);
		return r;
	}
	
	public static Response of(int code, String message) {
		Response r = new Response();
		r.code = code;
		r.message = message;
		r.statusText = getStatusText(code);
		new ResponseEntity<>(r, HttpStatus.valueOf(code));
		return r;
	}
	
	public static Response of(int code, String message, Object data) {
		Response r = new Response();
		r.code = code;
		r.message = message;
		r.data = data;
		r.statusText = getStatusText(code);
		return r;
	}
	
	public static ResponseEntity<?> build(int code) {
		Response r = new Response();
		r.code = code;
		r.message = getMessage(code);
		r.statusText = getStatusText(code);
		return ResponseEntity.status(HttpStatus.valueOf(code)).body(r);
	}
	
	public static ResponseEntity<?> build(int code, String message) {
		Response r = new Response();
		r.code = code;
		r.message = message;
		r.statusText = getStatusText(code);
		return ResponseEntity.status(HttpStatus.valueOf(code)).body(r);
	}
	
	public static ResponseEntity<?> build(int code, Object data) {
		Response r = new Response();
		r.code = code;
		r.message = getMessage(code);
		r.data = data;
		r.statusText = getStatusText(code);
		return ResponseEntity.status(HttpStatus.valueOf(code)).body(r);
	}
	
	public static ResponseEntity<?> build(int code, String message, Object data) {
		Response r = new Response();
		r.code = code;
		r.message = message;
		r.data = data;
		r.statusText = getStatusText(code);
		return ResponseEntity.status(HttpStatus.valueOf(code)).body(r);
	}
	
	private static String getStatusText (int code) {
		String statusText = null;
		switch (code ) {
			
			case Code.OK : 
				statusText = OK;
				break;
			case Code.CREATED : 
				statusText = CREATED;
				break;
			case Code.ACCEPTED : 
				statusText = ACCEPTED;
				break;
		}
		return statusText;
	}
	
	private static String getMessage (int code) {
		String statusMessage = null;
		switch (code ) {
			
			case Code.OK : 
				statusMessage = Messages.OK;
				break;
			case Code.CREATED : 
				statusMessage = Messages.CREATED;
				break;
			case Code.ACCEPTED : 
				statusMessage = Messages.ACCEPTED;
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