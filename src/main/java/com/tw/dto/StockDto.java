package com.tw.dto;

import lombok.Data;

@Data
public class StockDto {

	private Long id;
	private double grnqty;
	private double saleqty;
	private double saleReturnqty;
	private double stockqty;
	private double debitqty;
	private double creditqty;
	private double adjustqty;
	private double openingqty;
	private double stockin;
	private double stockout;
	private double salesvalue;
	private double purchasevalue;
	private double sqty;
	private ItemDto item;
	
}
