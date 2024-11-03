package com.tw.dto;

import java.util.Calendar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerWiseDto {

	private String customerName;
	private String customerMobile;
	private String saleNo;
	private Calendar saleDate;
	private String status;
	private double paidAmount ;
	private double remainingAmount ;
	private double netAmount;
	private Boolean paidflag;
	private Boolean stockflag;
	
}
