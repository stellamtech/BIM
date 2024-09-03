package com.tw.dto;

import java.util.Calendar;

import lombok.Data;
@Data
public class StockInOutDto {

	private Long id;
	private String type;
	private double qty;
	private double stockin;
	private double stockout;
	private double salesvalue;
	private double purchasevalue;
	private String refno;
	private String reason;
	private Calendar date;
	private Long itemId;
	private String itemName;
	//private ItemDto item;
	private String itemCode;
	
	
	
}
