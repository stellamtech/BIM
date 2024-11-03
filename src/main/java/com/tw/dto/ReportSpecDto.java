package com.tw.dto;

import java.util.Calendar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportSpecDto {

	private String customerName;
	private String customerMobile;
	private String saleNo;
	private Calendar saleDate;

}
