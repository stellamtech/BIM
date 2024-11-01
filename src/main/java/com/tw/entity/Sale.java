package com.tw.entity;

import java.util.Calendar;
import java.util.List;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("deprecation")
@Entity
@Table(name = "sale_invoice")
@Where(clause = "deleted=false")
@Data
@EqualsAndHashCode(callSuper=false)
public class Sale extends AbstractPersistable{
	/**
	 * BILAL
	 **/
	private static final long serialVersionUID = -8667731991640624887L;

	@Column(name = "sale_no", nullable = true)
	private String saleno;

	@Column(name = "sale_date", nullable = true)
	private Calendar saledate;

	@Column(name = "naration", nullable = true)
	private String naration;

	@Column(name = "gross_amt", nullable = true)
	private double grossAmt;

	@Column(name = "discount", nullable = true)
	private double discount;

	@Column(name = "net_amt", nullable = true)
	private double netAmt;

	@Column(name = "paid_amt", nullable = true)
	private double paidAmt;

	@Column(name = "remaining_amt", nullable = true)
	private double remainingAmt;

	@Column(name = "status", nullable = true)
	private String status;
	
	@Column(name = "paid_flag", nullable = false)
	private Boolean paidflag = false;
	
	@Column(name ="advance_amt", nullable = true)
	private double advanceAmt;
	
	@Column(name = "address", nullable = true)
	private String address;
	
	@Column(name = "customer_mobile", nullable = true)
	private String customerMobile;
	
	@Column(name = "customer_name")
	private String customerName;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinColumn(name = "customer_id")
	@JsonIgnore
	@NotFound(action = NotFoundAction.IGNORE)
	private Customer customer;

	@OneToMany(mappedBy = "sale", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JsonIgnore
	private List<SaleItem> saleItem;
	
	@Column(name = "stock_flag", nullable = false)
	private Boolean stockflag = false;
}
