package com.yzsquare.service;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yzsquare.model.Audition;
import com.yzsquare.repository.AuditionRepository;

@Service
public class AuditionService {
	private static final Log log = LogFactory.getLog(AuditionService.class);
	private AuditionRepository auditionRepository;

	@Autowired
	public AuditionService(AuditionRepository auditionRepository) {
		this.auditionRepository = auditionRepository;
	}

	public Audition save(Audition audition) {
		if (audition.getId() != null && auditionRepository.exists(audition.getId())) {
			log.error("There is already existing entity with such ID in the database.");
			throw new EntityExistsException("There is already existing entity with such ID in the database.");
		}

		return auditionRepository.save(audition);
	}

	public Audition update(Audition audition) {
		if (audition.getId() != null && !auditionRepository.exists(audition.getId())) {
			log.error("There is no entity with such ID in the database.");
			throw new EntityNotFoundException("There is no entity with such ID in the database.");
		}

		return auditionRepository.save(audition);
	}

	public List<Audition> findAll() {
		return auditionRepository.findAll();
	}

	public Audition findOne(Long id) {
		return auditionRepository.findOne(id);
	}

	public void delete(Long id) {
		auditionRepository.delete(id);
	}
}
