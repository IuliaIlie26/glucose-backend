package com.fils.glucose.exposition.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fils.glucose.exposition.dto.ConsultationFilterDto;
import com.fils.glucose.exposition.dto.PatientDto;
import com.fils.glucose.exposition.dto.ConsultationDto;

@RestController
@RequestMapping("api/consultations")
@CrossOrigin(origins = "http://localhost:2020")
public class ConsultationsController {

	
	private final ConsultationFacade consultationFacade;

	public ConsultationsController(ConsultationFacade consultationFacade) {
		this.consultationFacade = consultationFacade;
	}
	
	@PostMapping("getFreeSpots")
	public List<ConsultationDto> getFreeSpots(@RequestBody ConsultationFilterDto filter) {
		return consultationFacade.getFreeSpots(filter);
	}
	
	
	@PostMapping("saveConsultation")
	public void save(@RequestBody ConsultationDto dto) {
		 consultationFacade.saveConsultation(dto);
	}
	
	
	@GetMapping("getAllConsultations")
	public List<ConsultationDto> getAllConsultations(){
		return consultationFacade.getAllConsultations();
	}
	
	@PostMapping("delete")
	public void delete(@RequestBody ConsultationDto dto) {
		consultationFacade.delete(dto);
	}
	
	@GetMapping("getPatientsForDoctor")
	public List<PatientDto> getPatientsForDoctor(@RequestParam String username){
		return consultationFacade.getPatientsForDoctor(username);
	}
}
