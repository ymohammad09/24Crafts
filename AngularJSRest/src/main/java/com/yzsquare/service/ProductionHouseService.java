package com.yzsquare.service;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yzsquare.model.ProductionHouse;
import com.yzsquare.repository.ProductionHouseRepository;

@Service
public class ProductionHouseService {
	private static final Log log = LogFactory.getLog(ProductionHouseService.class);
	private ProductionHouseRepository productionHouseRepository;

	@Autowired
	public ProductionHouseService(ProductionHouseRepository productionHouseRepository) {
		this.productionHouseRepository = productionHouseRepository;
	}

	public ProductionHouse save(ProductionHouse productionHouse) {
		if (productionHouse.getId() != null && productionHouseRepository.exists(productionHouse.getId())) {
			log.error("There is already existing entity with such ID in the database.");
			throw new EntityExistsException("There is already existing entity with such ID in the database.");
		}

		return productionHouseRepository.save(productionHouse);
	}

	public ProductionHouse update(ProductionHouse productionHouse) {
		if (productionHouse.getId() != null && !productionHouseRepository.exists(productionHouse.getId())) {
			log.error("There is no entity with such ID in the database.");
			throw new EntityNotFoundException("There is no entity with such ID in the database.");
		}

		return productionHouseRepository.save(productionHouse);
	}

	public List<ProductionHouse> findAll() {
		return productionHouseRepository.findAll();
	}

	public ProductionHouse findOne(Long id) {
		return productionHouseRepository.findOne(id);
	}

	public void delete(Long id) {
		productionHouseRepository.delete(id);
	}
}
