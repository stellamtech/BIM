package com.tw.spec;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.tw.entity.StockInOut;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class StockinOutSpec implements Specification<StockInOut> {

	private static final long serialVersionUID = 1L;

	private String itemName;
	private String itemCode;

	public StockinOutSpec(String itemName, String itemCode) {
		super();
		this.itemName = itemName;
		this.itemCode = itemCode;

	}

	@Override
	public Predicate toPredicate(Root<StockInOut> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

		Predicate conjunction = cb.conjunction();

		if (StringUtils.isNotEmpty(this.itemName)) {
			conjunction.getExpressions().add(cb.like(root.get("item").get("itemName"), "%" + this.itemName + "%"));
		}

		if (StringUtils.isNotEmpty(this.itemCode)) {
			conjunction.getExpressions().add(cb.like(root.get("item").get("itemCode"), "%" + this.itemCode + "%"));
		}

		return conjunction;
	}

}
