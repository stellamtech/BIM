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

	private String name;
	private String code;
	
	
	
	public StockinOutSpec(String name, String code) {
		super();
		this.name = name;
		this.code = code;
		
		
	}


	@Override
	public Predicate toPredicate(Root<StockInOut> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

		Predicate conjunction = cb.conjunction();
		
		if( StringUtils.isNotEmpty( this.name ) ) {
			conjunction.getExpressions().add( cb.like( root.get("item").get("name") , "%" + this.name +"%" ) );
		}
		
		if( StringUtils.isNotEmpty( this.code ) ) {
			conjunction.getExpressions().add( cb.like( root.get("item").get("code") , "%" + this.code +"%" ) );
		}
		

		return conjunction;
	}

}
