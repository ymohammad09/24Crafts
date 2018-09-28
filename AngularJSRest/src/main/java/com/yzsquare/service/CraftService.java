package com.yzsquare.service;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yzsquare.model.Craft;
import com.yzsquare.repository.CraftRepository;

@Service
public class CraftService {
	private static final Log log = LogFactory.getLog(CraftService.class);
	private CraftRepository craftRepository;

	@Autowired
	public CraftService(CraftRepository craftRepository) {
		this.craftRepository = craftRepository;
	}

	public Craft save(Craft craft) {
		if (craft.getId() != null && craftRepository.exists(craft.getId())) {
			log.error("There is already existing entity with such ID in the database.");
			throw new EntityExistsException("There is already existing entity with such ID in the database.");
		}

		return craftRepository.save(craft);
	}

	public Craft update(Craft craft) {
		if (craft.getId() != null && !craftRepository.exists(craft.getId())) {
			log.error("There is no entity with such ID in the database.");
			throw new EntityNotFoundException("There is no entity with such ID in the database.");
		}

		return craftRepository.save(craft);
	}

	public List<Craft> findAll() {
		return craftRepository.findAll();
	}

	public Craft findOne(Long id) {
		return craftRepository.findOne(id);
	}

	public void delete(Long id) {
		craftRepository.delete(id);
	}
}
