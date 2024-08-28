package com.tw.enums;

public enum StatusType {
	
	Active("Active"), Disable("Disable"), Lock("Lock"),Deactive("Deactive"),Process("Processing");
	
	private String value;
	
	private StatusType(String statusType) {
		this.value = statusType;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
