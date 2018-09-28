package com.yzsquare.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yzsquare.model.Talent;
import com.yzsquare.service.TalentService;

@RestController
@RequestMapping("/api")
public class TalentResource {

	private static final Log log = LogFactory.getLog(TalentResource.class);
	private TalentService talentService;

	public TalentResource(TalentService talentService) {
		this.talentService = talentService;
	}

	@RequestMapping(value = "talent", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Talent> getAllTalent() {
		return talentService.findAll();
	}

	@RequestMapping(value = "talent", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Talent> createTalent(@RequestBody Talent talent) throws URISyntaxException {
		try {
			Talent result = talentService.save(talent);
			return ResponseEntity.created(new URI("/api/talent/" + result.getId())).body(result);
		} catch (EntityExistsException e) {
			log.error(HttpStatus.CONFLICT);
			return new ResponseEntity<Talent>(HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "talent", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Talent> updateTalent(@RequestBody Talent talent) throws URISyntaxException {
		if (talent.getId() == null) {
			log.error(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Talent>(HttpStatus.NOT_FOUND);
		}

		try {
			Talent result = talentService.update(talent);

			return ResponseEntity.created(new URI("/api/talent/" + result.getId())).body(result);
		} catch (EntityNotFoundException e) {
			log.error(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Talent>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/talent/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteTalent(@PathVariable Long id) {
		talentService.delete(id);

		return ResponseEntity.ok().build();
	}
}
