package com.tw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tw.dto.ItemRepSpecDto;
import com.tw.dto.ReportSpecDto;
import com.tw.service.ReportService;


@RestController
@RequestMapping("report/")
public class ReportController {

	@Autowired
	private ReportService reportService;
	
	@PostMapping("customerWise")
	public ResponseEntity<?> customerWise(@RequestBody ReportSpecDto dto) {
		return reportService.customerWise(dto);
	}
	
	@PostMapping("itemWise")
	public ResponseEntity<?> itemWise(@RequestBody ItemRepSpecDto dto) {
		return reportService.itemWise(dto);
	}

	
}
