package com.tw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tw.dto.SaleDto;
import com.tw.service.SaleService;
import com.tw.spec.SaleSpecDto;


@RestController
@RequestMapping("sale/")
public class SaleController {

	@Autowired
	private SaleService saleService;
	
	@PostMapping(value ="add")
	public ResponseEntity<?> addSale(@RequestBody SaleDto dto) {
		return saleService.addSale(dto);
	}
	@GetMapping("get/{id}")
	public ResponseEntity<?> getSale(@PathVariable Long id){
		return saleService.getSale(id);
	}
	
	@GetMapping("delete/{id}")
	public ResponseEntity<?> deleteSale(@PathVariable Long id){
		return saleService.deleteSale(id);
	}
	
	@PostMapping("list")
	public ResponseEntity<?> getSaleList(@RequestBody SaleSpecDto dto){
		return saleService.getSaleList(dto);
	}
	@GetMapping("getbyCustomer/{id}")
	public ResponseEntity<?> getbyCustomer(@PathVariable Long id){
		return saleService.getbyCustomer(id);
	}
	
	@GetMapping("deleteitem/{id}")
	public ResponseEntity<?> deleteitem(@PathVariable Long id){
		return saleService.deleteitem(id);
	}
	
//	@GetMapping("print/{id}")
//	public ResponseEntity<?> getPrint(@PathVariable Long id){
//		return saleService.getPrint(id);
//	}
	
//	@GetMapping("getIds/{id}")
//	public ResponseEntity<?> getIds(@PathVariable Long id){
//		return saleService.getIds(id);
//	}
}
