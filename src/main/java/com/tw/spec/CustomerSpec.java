package com.tw.spec;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.tw.entity.Customer;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@SuppressWarnings("serial")
public class CustomerSpec implements Specification<Customer> {
	private String customerName;
	private String mobileNo;

	public CustomerSpec(String customerName, String mobileNo) {
		this.customerName = customerName;
		this.mobileNo = mobileNo;
	}

	@Override
	public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (customerName != null && !customerName.isEmpty()) {
			predicates.add(criteriaBuilder.like(root.get("customerName"), "%" + customerName + "%"));
		}
		if (mobileNo != null && !mobileNo.isEmpty()) {
			predicates.add(criteriaBuilder.equal(root.get("mobileNo"), mobileNo));
		}
		return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
	}
}
