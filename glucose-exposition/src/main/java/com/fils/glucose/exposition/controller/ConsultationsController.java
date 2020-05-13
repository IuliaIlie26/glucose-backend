package com.fils.glucose.exposition.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fils.glucose.exposition.dto.ConsultationDto;
import com.fils.glucose.exposition.dto.ConsultationSpotDto;

@RestController
@RequestMapping("api/consultations")
@CrossOrigin(origins = "http://localhost:2020")
public class ConsultationsController {

	
	private final ConsultationFacade consultationFacade;

	public ConsultationsController(ConsultationFacade consultationFacade) {
		this.consultationFacade = consultationFacade;
	}
	
	@PostMapping("getFreeSpots")
	public List<ConsultationSpotDto> getFreeSpots(@RequestBody ConsultationDto filter) {
		return consultationFacade.getFreeSpots(filter);
	}
	
}
