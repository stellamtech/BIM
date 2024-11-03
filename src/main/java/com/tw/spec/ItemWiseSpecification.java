package com.tw.spec;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.tw.dto.ItemRepSpecDto;
import com.tw.entity.Sale;

import jakarta.persistence.criteria.Predicate;

public class ItemWiseSpecification {
	public static Specification<Sale> buildSpecification(ItemRepSpecDto dto) {
		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();

			if (StringUtils.isNotEmpty(dto.getItemName())) {
				predicates.add(criteriaBuilder.like(root.get("saleItem").get("item").get("itemName"), "%" + dto.getItemName() + "%"));
			}
			if (StringUtils.isNotEmpty(dto.getCustomerName())) {
				predicates.add(criteriaBuilder.like(root.get("customerName"), "%" + dto.getCustomerName() + "%"));
			}
			if (StringUtils.isNotEmpty(dto.getCustomerMobile())) {
				predicates.add(criteriaBuilder.like(root.get("customerMobile"), "%" + dto.getCustomerMobile() + "%"));
			}
			if (StringUtils.isNotEmpty(dto.getSaleNo())) {
				predicates.add(criteriaBuilder.like(root.get("saleno"), "%" + dto.getSaleNo() + "%"));
			}
			if (dto.getSaleDate() != null) {
				Calendar receiptDate = dto.getSaleDate();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String receiptDateString = dateFormat.format(receiptDate.getTime());
				predicates.add(
						criteriaBuilder.like(root.get("saledate").as(String.class), "%" + receiptDateString + "%"));
			}

			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}
}
