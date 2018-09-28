package com.yzsquare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yzsquare.model.ProductionHouse;

public interface ProductionHouseRepository extends JpaRepository<ProductionHouse, Long> {
	ProductionHouse findById(Long id);
}
