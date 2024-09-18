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
@Table(name = "sale_invoice_item")
@Where(clause = "deleted=false")
@Data
@EqualsAndHashCode(callSuper=false)
public class SaleItem extends AbstractPersistable {
	
	/**
	 * BILAL
	 **/
	private static final long serialVersionUID = -8667731991640624887L;

	@Column(name = "qty", nullable = true)
	private double qty;

	@Column(name = "unit_price", nullable = true)
	private double unitPrice;

	@Column(name = "amount", nullable = true)
	private double amount;

	@Column(name = "discount_perc", nullable = true)
	private double discountPerc;

	@Column(name = "discount", nullable = true)
	private double discount;

	@Column(name = "total", nullable = true)
	private double total;

	@Column(name = "reference", nullable = true)
	private String reference;
	
	@Column(name = "product_name", nullable = true)
	private String productname;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinColumn(name = "item_id")
	@JsonIgnore
	@NotFound(action = NotFoundAction.IGNORE)
	private Item item;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinColumn(name = "sale_invoice_id")
	@JsonIgnore
	@NotFound(action = NotFoundAction.IGNORE)
	private Sale sale;

	
}
