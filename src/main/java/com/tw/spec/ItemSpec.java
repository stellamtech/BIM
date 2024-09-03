package com.tw.spec;


import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.tw.entity.Item;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;



public class ItemSpec implements Specification<Item> {


	private static final long serialVersionUID = 1L;

	private String name;
	private String code;
	private String type;
	
	
	public ItemSpec(String name, String sku, String type) {
		super();
		this.name = name;
		this.code = sku;
		this.type = type;
		
	}


	@Override
	public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

		Predicate conjunction = cb.conjunction();
		
		if( StringUtils.isNotEmpty( this.name ) ) {
			conjunction.getExpressions().add( cb.like( root.get("name") , "%" + this.name +"%" ) );
		}
		
		if( StringUtils.isNotEmpty( this.code ) ) {
			conjunction.getExpressions().add( cb.like( root.get("code") , "%" + this.code +"%" ) );
		}
		
		if( StringUtils.isNotEmpty( this.type ) ) {
			conjunction.getExpressions().add( cb.like( root.get("type") , "%" + this.type +"%" ) );
		}
		
		
		return conjunction;
	}

}
