package com.tw.dto;

import java.util.Calendar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleItemReportDto {

	private String itemName;
	private double qty;
	private double unitPrice;
	private double itemAmount;
	
	private String customerName;
	private String customerMobile;
	private Calendar saleDate;
	private String saleNo;

	private double netAmt;
	private double paidAmt;
	private double remainingAmt;
	private String status;
	private Boolean paidflag;
	private Boolean stockflag;
}
