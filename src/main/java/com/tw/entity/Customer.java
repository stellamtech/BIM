package com.tw.entity;

import org.hibernate.annotations.Where;

import com.tw.generics.AbstractPersistable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("deprecation")
@Entity
@Table(name = "customer")
@Where(clause = "deleted=false")
@Data
@EqualsAndHashCode(callSuper=false)
public class Customer extends AbstractPersistable {
	private static final long serialVersionUID = -8667731991640624887L;

	@Column(name = "first_name", nullable = true)
	private String firstName;

	@Column(name = "last_name", nullable = true)
	private String lastName;

	@Column(name = "customer_name", nullable = true)
	private String customerName;

	@Column(name = "mobile_no", nullable = true)
	private String mobileNo;
	
	@Column(name = "address", nullable = true)
	private String address;

	@Column(name = "remark", nullable = true)
	private String remark;
	
	@Column(name = "accno", nullable = true)
	private String accno;
	
//	@Column(name = "type", nullable = true)
//	private String type;
	
//	@Column(name = "email", nullable = true)
//	private String email;
//
//	@Column(name = "website", nullable = true)
//	private String website;
//
//	@Column(name = "currency", nullable = true)
//	private String currency;
//
//	@Column(name = "payment_terms", nullable = true)
//	private String paymentTerms;
//
//	@Column(name = "trn_no", nullable = true)
//	private String trnno;
//


}
