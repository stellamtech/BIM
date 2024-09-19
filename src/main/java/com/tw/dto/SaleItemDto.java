package com.tw.dto;

import lombok.Data;

@Data
public class SaleItemDto {

	private Long id;
	private double qty;
	private double unitPrice;
	private double amount;
	private double discountPerc;
	private double discount;
	private double total;
	private String reference;
	private Long itemId;

	
}
