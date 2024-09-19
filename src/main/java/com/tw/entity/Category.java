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
@Table(name = "category")
@Data
@EqualsAndHashCode(callSuper=false)
@Where(clause = "deleted=false")
public class Category extends AbstractPersistable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1437948407328251554L;

	@Column(name="category_name")
	private String categoryName;
	
	
	private String categoryCode;
	
}
