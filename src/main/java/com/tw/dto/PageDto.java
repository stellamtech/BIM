package com.tw.dto;

public class PageDto {

	private Object data;
	private Long totalCount;
	
	public PageDto(Object data, Long totalCount) {
		super();
		this.data = data;
		this.totalCount = totalCount;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
}
