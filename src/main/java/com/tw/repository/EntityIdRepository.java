package com.tw.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tw.entity.EntityIdRep;

public interface EntityIdRepository extends JpaRepository<EntityIdRep, Long> {

	EntityIdRep findOneByModuleName(String string);

}
