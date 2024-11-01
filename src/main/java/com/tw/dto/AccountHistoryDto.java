package com.tw.dto;

import lombok.Data;

@Data
public class AccountHistoryDto {

	private Long saleId;
	private String voucherno;
	private String type;
	private double netAmt;
	private double paidAmt;
	private double remainingAmt;
	private String stockReturn;
	
}
