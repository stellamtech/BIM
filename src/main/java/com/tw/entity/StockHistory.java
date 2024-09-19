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
@Table(name = "stock_history")
@Where(clause = "deleted=false")
@Data
@EqualsAndHashCode(callSuper=false)
public class StockHistory extends AbstractPersistable {
	private static final long serialVersionUID = -8667731991640624887L;

	@Column(name = "voucherno", nullable = true)
	private String voucherno;

	@Column(name = "type", nullable = true)
	private String type;

	@Column(name = "qty", nullable = true)
	private double qty;

//	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
//	@JoinColumn(name = "purchase_id")
//	@JsonIgnore
//	@NotFound(action = NotFoundAction.IGNORE)
//	private GrnItem grnitem;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinColumn(name = "sale_id")
	@JsonIgnore
	@NotFound(action = NotFoundAction.IGNORE)
	private SaleItem saleitem;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinColumn(name = "stock_inout_id")
	@JsonIgnore
	@NotFound(action = NotFoundAction.IGNORE)
	private StockInOut sio;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinColumn(name = "item_id")
	@JsonIgnore
	@NotFound(action = NotFoundAction.IGNORE)
	private Item item;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinColumn(name = "customer_id")
	@JsonIgnore
	@NotFound(action = NotFoundAction.IGNORE)
	private Customer customer;

	@Column(name = "status")
	private String status;

	
}
