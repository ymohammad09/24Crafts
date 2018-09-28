package com.yzsquare.service;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yzsquare.model.Talent;
import com.yzsquare.repository.TalentRepository;

@Service
public class TalentService {
	private static final Log log = LogFactory.getLog(TalentService.class);
	private TalentRepository talentRepository;

	@Autowired
	public TalentService(TalentRepository talentRepository) {
		this.talentRepository = talentRepository;
	}

	public Talent save(Talent talent) {
		if (talent.getId() != null && talentRepository.exists(talent.getId())) {
			log.error("There is already existing entity with such ID in the database.");
			throw new EntityExistsException("There is already existing entity with such ID in the database.");
		}

		return talentRepository.save(talent);
	}

	public Talent update(Talent talent) {
		if (talent.getId() != null && !talentRepository.exists(talent.getId())) {
			log.error("There is no entity with such ID in the database.");
			throw new EntityNotFoundException("There is no entity with such ID in the database.");
		}

		return talentRepository.save(talent);
	}

	public List<Talent> findAll() {
		return talentRepository.findAll();
	}

	public Talent findOne(Long id) {
		return talentRepository.findOne(id);
	}

	public void delete(Long id) {
		talentRepository.delete(id);
	}
}
