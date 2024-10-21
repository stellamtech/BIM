package com.tw.spec;

import java.sql.Date;

import lombok.Data;

@Data
public class SaleSpecDto {
	
	private Integer page;
	private Integer size;
	private String saleno;
	private String customerMobile;
	private String customername;
	private String status;
	private Date saleDate;

}
