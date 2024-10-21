package com.tw.dto;

import java.util.Calendar;
import java.util.List;

import lombok.Data;

@Data
public class SaleListDto {

	private Long id;
	private String saleno;
	private Calendar saledate;
	private String customerName;
	private String customerMobile;
	private double grossAmt;
	private double discount;
	
	private double netAmt;
	private double paidAmt;
	private double remainingAmt;
	private String status;
	private List<SaleItemDto> saleItemDto;
}
