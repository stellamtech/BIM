package com.tw.service;

import org.springframework.http.ResponseEntity;

import com.tw.dto.SaleDto;

public interface SaleService {

	ResponseEntity<?> addSale(SaleDto dto);

	ResponseEntity<?> getSale(Long id);

	ResponseEntity<?> deleteSale(Long id);

	ResponseEntity<?> getbyCustomer(Long id);

	ResponseEntity<?> deleteitem(Long id);

	ResponseEntity<?> getPrint(Long id);

	ResponseEntity<?> getIds(Long id);

}
