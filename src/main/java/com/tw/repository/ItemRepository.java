package com.tw.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tw.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long>,JpaSpecificationExecutor<Item>{

	Optional<Item> findOneByItemName(String itemName);

	Optional<Item> findOneByItemCode(String itemCode);

}
