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
import com.fils.glucose.exposition.dto.ConsultationNotesDto;
import com.fils.glucose.exposition.dto.PatientDto;
import com.fils.glucose.exposition.facade.ConsultationFacade;
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
	public List<ConsultationDto> getAllConsultations() {
		return consultationFacade.getAllConsultations();
	}

	@PostMapping("delete")
	public void delete(@RequestBody ConsultationDto dto) {
		consultationFacade.delete(dto);
	}

	@GetMapping("getPatientsForDoctor")
	public List<PatientDto> getPatientsForDoctor(@RequestParam String username) {
		return consultationFacade.getPatientsForDoctor(username);
	}

	@GetMapping("getPatientConsultations")
	public List<ConsultationDto> getPatientConsultations(@RequestParam Long patientId) {
		return consultationFacade.getPatientConsultations(patientId);
	}
	
	@GetMapping("getConsultationById")
	public ConsultationDto getConsultationById(@RequestParam String consultationId) {
		return consultationFacade.getConsultationById(consultationId);
	}
	
	@GetMapping("getConsultationNote")
	public ConsultationNotesDto getConsultationNote(@RequestParam String consultationId) {
		return consultationFacade.getConsultationNote(consultationId);
	}
	
	@GetMapping("getNextConsultationsForDoctor")
	public List<ConsultationDto> getNextConsultationsForDoctor(@RequestParam String username){
		return consultationFacade.getNextConsultationsForDoctor(username);
	}
	
	@GetMapping("getCurrentConsultation")
	public ConsultationDto getCurrentConsultation(@RequestParam String username) {
		return consultationFacade.getCurrentConsultation(username);
	}
	
	@GetMapping("getTodaysConsultations")
	public List<ConsultationDto> getTodaysConsultations(@RequestParam String username){
		return consultationFacade.getTodaysConsultations(username);
	}
	
	@PostMapping("saveNotes")
	public void saveNotes(@RequestBody ConsultationNotesDto notes) {
		consultationFacade.saveNotes(notes);
	}
}
