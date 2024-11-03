package com.tw.dto;

import java.util.Calendar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemRepSpecDto {

	private String itemName;
	private String customerName;
	private String customerMobile;
	private Calendar saleDate;
	private String saleNo;
}
