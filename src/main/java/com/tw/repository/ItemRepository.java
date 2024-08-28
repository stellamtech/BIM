package com.tw.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tw.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{

}
