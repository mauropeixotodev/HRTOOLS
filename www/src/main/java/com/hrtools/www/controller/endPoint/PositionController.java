package com.hrtools.www.controller.endPoint;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.hrtools.www.controller.request.PositionRequest;
import com.hrtools.www.controller.response.PositionResponse;
import com.hrtools.www.service.PositionService;

import javax.persistence.EntityNotFoundException;

@RestController
public class PositionController {

	@Autowired
	private PositionService positionService;

	@GetMapping("/position")
	public List<PositionResponse> listPositions() throws Exception {
		return positionService.listPositions();
	}

	@PostMapping("/position")
	public ResponseEntity<PositionResponse> createPosition(@RequestBody @Validated PositionRequest positionRequest,
			UriComponentsBuilder uriBuilder) throws Exception {
		PositionResponse position = positionService.createPosition(positionRequest);
		URI uri = uriBuilder.path("/position/{id}").buildAndExpand(position.getId()).toUri();
		return ResponseEntity.created(uri).body(position);
	}

	@PutMapping("/position/{id}")
	public ResponseEntity<PositionResponse> updatePosition(@RequestBody @Validated PositionRequest positionRequest,
			@PathVariable Long id) throws Exception {
		try {
			return ResponseEntity.ok(positionService.updatePosition(positionRequest, id));
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/position/{id}")
	public ResponseEntity<PositionResponse> findPosition(@PathVariable Long id) throws Exception {
		try {
			return ResponseEntity.ok(positionService.findPosition(id));
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
