package com.tw.spec;

import org.springframework.data.jpa.domain.Specification;

import com.tw.entity.Sale;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.sql.Date;

import org.apache.commons.lang3.StringUtils;

public class SaleSpec implements Specification<Sale> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String customerMobile;
	private String customername;
	private String saleno;
	private String status;
	private Date saleDate;

	public SaleSpec(String customerMobile, String customername, String saleno, String status,Date saleDate) {
		this.customerMobile = customerMobile;
		this.customername = customername;
		this.saleno = saleno;
		this.status = status;
		this.saleDate=saleDate;
	}

	@Override
	public Predicate toPredicate(Root<Sale> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		Predicate conjunction = cb.conjunction();

		if (StringUtils.isNotEmpty(this.customerMobile)) {
			conjunction.getExpressions().add(cb.like(root.get("customerMobile"), "%" + this.customerMobile + "%"));
		}

		if (StringUtils.isNotEmpty(this.customername)) {
			conjunction.getExpressions().add(cb.like(root.get("customerName"), "%" + this.customername + "%"));
		}

		if (StringUtils.isNotEmpty(this.saleno)) {
			conjunction.getExpressions().add(cb.like(root.get("saleno"), "%" + this.saleno + "%"));
		}
		
		if (StringUtils.isNotEmpty(this.status)) {
		    conjunction.getExpressions().add(cb.equal(root.get("status"), this.status));
		}
		if (saleDate != null) {
		    // Assuming saleDate is the exact date you want to search for without time
		    Date startOfDay = Date.valueOf(saleDate.toLocalDate());  // Set to the start of the day
		    Date endOfDay = Date.valueOf(saleDate.toLocalDate().plusDays(1));  // Set to start of the next day
		    
		    conjunction.getExpressions().add(cb.between(root.get("saledate"), startOfDay, endOfDay));
		}

		
		return conjunction;
	}

}
