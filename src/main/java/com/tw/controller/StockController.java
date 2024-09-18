package com.tw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tw.dto.ItemSpecDto;
import com.tw.dto.StockInOutDto;
import com.tw.service.StockService;

@RestController
@RequestMapping("stock/")
//@CrossOrigin
public class StockController {

	@Autowired
	private StockService stockService;
	
	@PostMapping(value ="adjust")
	public ResponseEntity<?> adjustStock(@RequestBody StockInOutDto dto) {
		return stockService.adjustStock(dto);
	}
	@GetMapping(value ="byItem/{id}")
	public ResponseEntity<?> byItemId(@PathVariable Long id) {
		return stockService.byItemId(id);
	}
	@GetMapping(value ="byStockInOut/{id}")
	public ResponseEntity<?> byStockInOut(@PathVariable Long id) {
		return stockService.byStockInOut(id);
	}
	
	@PostMapping(value = "list")
	public ResponseEntity<?> stockList(@RequestBody ItemSpecDto spectDto) {
		return stockService.stockList(spectDto);
	}
}
