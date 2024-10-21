package com.tw.dto;

import java.util.Calendar;
import java.util.List;

import lombok.Data;

@Data
public class SaleEditDto {

	private Long id;
	private String saleno;
	private Calendar saledate;
	private String naration;
	private double grossAmt;
	private double discount;

	private double netAmt;
	private double paidAmt;
	private double remainingAmt;
	private String status;
	private CustomerDto customer;
	private List<SaleItemDto> saleItem;
	private double advanceAmt;
	private String address;
	private String customerMobile;
	public String customerName;
}
