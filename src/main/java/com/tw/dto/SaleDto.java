package com.tw.dto;

import java.util.Calendar;
import java.util.List;

import lombok.Data;

@Data
public class SaleDto {

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
	private Long customerId;
	private List<SaleItemDto> saleItem;

	// private Boolean updatestock;
	private double advanceAmt;
	private String address;
	private String customerMobile;
	public String customerName;

}
