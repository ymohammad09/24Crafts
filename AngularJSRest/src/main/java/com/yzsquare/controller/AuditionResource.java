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

import com.yzsquare.model.Audition;
import com.yzsquare.service.AuditionService;

@RestController
@RequestMapping("/api")
public class AuditionResource {

	private static final Log log = LogFactory.getLog(AuditionResource.class);
	private AuditionService auditionService;

	public AuditionResource(AuditionService auditionService) {
		this.auditionService = auditionService;
	}

	@RequestMapping(value = "audition", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Audition> getAllAudition() {
		return auditionService.findAll();
	}

	@RequestMapping(value = "audition", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Audition> createAudition(@RequestBody Audition audition) throws URISyntaxException {
		try {
			Audition result = auditionService.save(audition);
			return ResponseEntity.created(new URI("/api/audition/" + result.getId())).body(result);
		} catch (EntityExistsException e) {
			log.error(HttpStatus.CONFLICT);
			return new ResponseEntity<Audition>(HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "audition", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Audition> updateAudition(@RequestBody Audition audition) throws URISyntaxException {
		if (audition.getId() == null) {
			log.error(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Audition>(HttpStatus.NOT_FOUND);
		}

		try {
			Audition result = auditionService.update(audition);

			return ResponseEntity.created(new URI("/api/audition/" + result.getId())).body(result);
		} catch (EntityNotFoundException e) {
			log.error(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Audition>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/audition/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteAudition(@PathVariable Long id) {
		auditionService.delete(id);

		return ResponseEntity.ok().build();
	}
}
