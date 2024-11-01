package com.tw.spec;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.tw.entity.Sale;

import jakarta.persistence.criteria.Predicate;

public class SaleSpecification {

	public static Specification<Sale> buildSpecification(SaleSpecDto dto) {

		return (root, query, criteriaBuilder) -> {

			List<Predicate> predicates = new ArrayList<>();

			if (StringUtils.isNotEmpty(dto.getCustomername())) {
				predicates.add(criteriaBuilder.like(root.get("customerName"), "%" + dto.getCustomername() + "%"));
			}

			if (StringUtils.isNotEmpty(dto.getCustomerMobile())) {
				predicates.add(criteriaBuilder.like(root.get("customerMobile"), "%" + dto.getCustomerMobile() + "%"));
			}

			if (StringUtils.isNotEmpty(dto.getStatus())) {
				predicates.add(criteriaBuilder.equal(root.get("status"), dto.getStatus()));
			}
			
			if (StringUtils.isNotEmpty(dto.getSaleno())) {
				predicates.add(criteriaBuilder.like(root.get("saleno"),"%" +  dto.getSaleno() + "%"));
			}
			
			if (dto.getSaleDate() != null) {
//				Date startOfDay = Date.valueOf(dto.getSaleDate().toLocalDate()); // Set to the start of the day
//				Date endOfDay = Date.valueOf(dto.getSaleDate().toLocalDate().plusDays(1)); // Set to start of the next
//
//				predicates.add(criteriaBuilder.between(root.get("saledate"), startOfDay, endOfDay));
				LocalDate saleDate = dto.getSaleDate().toLocalDate();
				predicates.add(criteriaBuilder.equal(root.get("saledate"), saleDate));

			}

			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}
}
