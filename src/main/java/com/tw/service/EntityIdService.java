package com.tw.service;

import org.springframework.http.ResponseEntity;

public interface EntityIdService {

	ResponseEntity<?> entityidrep(String type);

	String getvocher(String type);

}
