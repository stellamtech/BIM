package com.tw.service;

import org.springframework.http.ResponseEntity;

import com.tw.dto.ItemSpecDto;
import com.tw.dto.StockInOutDto;

public interface StockService {

	ResponseEntity<?> adjustStock(StockInOutDto dto);

	ResponseEntity<?> byItemId(Long id);

	ResponseEntity<?> byStockInOut(Long id);

	ResponseEntity<?> stockList(ItemSpecDto spectDto);

}
