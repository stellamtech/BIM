package com.tw.entity;

import java.math.BigDecimal;

import org.hibernate.annotations.Where;

import com.tw.generics.AbstractPersistable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("deprecation")
@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Table(name = "item")
@Where(clause = "deleted=false")
public class Item extends AbstractPersistable{/**
	 * 
	 */
	private static final long serialVersionUID = -8609013087586118508L;
	
	@Column(name = "item_code")
	private String itemCode;
	
	@Column(name = "item_name")
	private String itemName;
	
	@Column(name = "item_price")
	private BigDecimal itemPrice;
	
}
