package com.tw.entity;

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
@Table(name ="stock")
@Where(clause = "deleted=false")
@Data
@EqualsAndHashCode(callSuper=false)
public class Stock extends AbstractPersistable {
	private static final long serialVersionUID = -8667731991640624887L;
	
	@Column(name ="stock_in", nullable = true)
	private double stockin;
	
	@Column(name ="stock_out", nullable = true)
	private double stockout;
	
	@Column(name ="grn_qty", nullable = true)
	private double grnqty;
	
	@Column(name ="sale_qty", nullable = true)
	private double saleqty;
	
	@Column(name ="sale_return_qty", nullable = true)
	private double saleReturnqty;
	
	@Column(name ="stock_qty", nullable = true)
	private double stockqty;
	
	@Column(name ="debit_qty", nullable = true)
	private double debitqty;
	
	@Column(name ="credit_qty", nullable = true)
	private double creditqty;

	@Column(name ="adjust_qty", nullable = true)
	private double adjustqty;
	
	@Column(name ="opening_qty", nullable = true)
	private double openingqty;
	
	@Column(name ="sales_value", nullable = true)
	private double salesvalue;
	
	@Column(name ="purchase_value", nullable = true)
	private double purchasevalue;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name="item_id")
	@JsonIgnore	
	@NotFound(action = NotFoundAction.IGNORE)
	private Item item;
	
}
