package com.tw.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ItemDto {

	private Long id;
	private String itemCode;
	private String itemName;
	private BigDecimal itemPrice;
	private double stockinhand;
}
