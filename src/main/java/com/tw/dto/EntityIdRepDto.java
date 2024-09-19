package com.tw.dto;

import lombok.Data;

@Data
public class EntityIdRepDto {

	private Long id;
	private Long lastsrno;
	private String moduleName;
	private String prefix;
	private String suffix;
	private String description;
	private String value;
	private String key;
}
