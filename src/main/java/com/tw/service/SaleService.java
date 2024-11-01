package com.tw.service;

import org.springframework.http.ResponseEntity;

import com.tw.dto.AccountHistoryDto;
import com.tw.dto.SaleDto;
import com.tw.spec.SaleSpecDto;

public interface SaleService {

	ResponseEntity<?> addSale(SaleDto dto);

	ResponseEntity<?> getSale(Long id);

	ResponseEntity<?> deleteSale(Long id);

	ResponseEntity<?> getbyCustomer(Long id);

	ResponseEntity<?> deleteitem(Long id);

	ResponseEntity<?> getPrint(Long id);


	ResponseEntity<?> getSaleList(SaleSpecDto dto);

	ResponseEntity<?> salePayment(AccountHistoryDto dto);

}
