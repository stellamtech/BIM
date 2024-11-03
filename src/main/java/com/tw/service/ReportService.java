package com.tw.service;

import org.springframework.http.ResponseEntity;

import com.tw.dto.ItemRepSpecDto;
import com.tw.dto.ReportSpecDto;

public interface ReportService {

	ResponseEntity<?> customerWise(ReportSpecDto dto);

	ResponseEntity<?> itemWise(ItemRepSpecDto dto);

}
