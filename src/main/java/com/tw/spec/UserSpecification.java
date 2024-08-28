package com.tw.spec;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.tw.dto.UserSpecDto;
import com.tw.entity.User;

import jakarta.persistence.criteria.Predicate;

public class UserSpecification {

	public static Specification<User> buildSpecification1(UserSpecDto dto) {

		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();

			if (StringUtils.isNotEmpty(dto.getUsername())) {
				predicates.add(criteriaBuilder.like(root.get("userName"), "%" + dto.getUsername() + "%"));
			}

			if (StringUtils.isNotEmpty(dto.getName())) {
				predicates.add(criteriaBuilder.like(root.get("name"), "%" + dto.getName() + "%"));
			}
			if (StringUtils.isNotEmpty(dto.getMobile())) {
				predicates.add(criteriaBuilder.equal(root.get("mobileNo"), "%" + dto.getMobile() + "%"));
			}
			if (StringUtils.isNotEmpty(dto.getEmail())) {
				predicates.add(criteriaBuilder.equal(root.get("email"), "%" + dto.getEmail() + "%"));
			}
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}
}
