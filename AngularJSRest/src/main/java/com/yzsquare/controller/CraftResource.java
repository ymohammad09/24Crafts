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

import com.yzsquare.model.Craft;
import com.yzsquare.service.CraftService;

@RestController
@RequestMapping("/api")
public class CraftResource {

	private static final Log log = LogFactory.getLog(CraftResource.class);
	private CraftService craftService;

	public CraftResource(CraftService craftService) {
		this.craftService = craftService;
	}

	@RequestMapping(value = "crafts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Craft> getAllCraft() {
		return craftService.findAll();
	}

	@RequestMapping(value = "crafts", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Craft> createCraft(@RequestBody Craft craft) throws URISyntaxException {
		try {
			Craft result = craftService.save(craft);
			return ResponseEntity.created(new URI("/api/craft/" + result.getId())).body(result);
		} catch (EntityExistsException e) {
			log.error(HttpStatus.CONFLICT);
			return new ResponseEntity<Craft>(HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "crafts", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Craft> updateCraft(@RequestBody Craft craft) throws URISyntaxException {
		if (craft.getId() == null) {
			log.error(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Craft>(HttpStatus.NOT_FOUND);
		}

		try {
			Craft result = craftService.update(craft);

			return ResponseEntity.created(new URI("/api/craft/" + result.getId())).body(result);
		} catch (EntityNotFoundException e) {
			log.error(HttpStatus.NOT_FOUND);
			return new ResponseEntity<Craft>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/crafts/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteCraft(@PathVariable Long id) {
		craftService.delete(id);

		return ResponseEntity.ok().build();
	}
}
