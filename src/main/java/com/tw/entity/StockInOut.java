package com.tw.entity;

import java.util.Calendar;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tw.generics.AbstractPersistable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("deprecation")
@Entity
@Table(name ="stockinout")
@Where(clause = "deleted=false")
@Data
@EqualsAndHashCode(callSuper=false)
public class StockInOut extends AbstractPersistable{
	private static final long serialVersionUID = -8667731991640624887L;
	
	@Column(name ="type", nullable = true)
	private String type;
	
	@Column(name ="qty", nullable = true)
	private double qty;
	
	@Column(name ="stock_in", nullable = true)
	private double stockin;
	                     
	@Column(name ="stock_out", nullable = true)
	private double stockout;
	
	@Column(name ="sales_value", nullable = true)
	private double salesvalue;
	
	@Column(name ="purchase_value", nullable = true)
	private double purchasevalue;
	
	@Column(name ="refno", nullable = true)
	private String refno;
	
	@Column(name ="reason", nullable = true)
	private String reason;
	
	@Column(name ="_date", nullable = true)
	private Calendar date;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name="item_id")
	@JsonIgnore	
	@NotFound(action = NotFoundAction.IGNORE)
	private Item item;
	
}
