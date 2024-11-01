package com.tw.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tw.generics.AbstractPersistable;

@SuppressWarnings("deprecation")
@Entity
@Table(name = "account_history")
@Where(clause = "deleted=false")
@Data
@EqualsAndHashCode(callSuper = false)
public class AccountHistory extends AbstractPersistable {
	private static final long serialVersionUID = -8667731991640624887L;

	@Column(name = "voucherno", nullable = true)
	private String voucherno;

	@Column(name = "type", nullable = true)
	private String type;

	@Column(name = "amount", nullable = true)
	private double amount;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinColumn(name = "sale_id")
	@JsonIgnore
	@NotFound(action = NotFoundAction.IGNORE)
	private Sale sale;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinColumn(name = "customer_id")
	@JsonIgnore
	@NotFound(action = NotFoundAction.IGNORE)
	private Customer customer;

	@Column(name = "status")
	private String status;
	
	@Column(name = "net_amt", nullable = true)
	private double netAmt;

	@Column(name = "paid_amt", nullable = true)
	private double paidAmt;

	@Column(name = "remaining_amt", nullable = true)
	private double remainingAmt;

}
