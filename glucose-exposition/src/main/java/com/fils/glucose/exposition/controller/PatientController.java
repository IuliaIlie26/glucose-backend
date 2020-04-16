package com.fils.glucose.exposition.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fils.glucose.exposition.dto.SavePatientDto;
import com.fils.glucose.exposition.service.PatientFacade;

@RestController
@RequestMapping("api/patient")
@CrossOrigin(origins = "http://localhost:2020")
public class PatientController {

	private final PatientFacade patientFacade;
	
	public PatientController(PatientFacade patientFacade) {
		this.patientFacade =patientFacade;
	}
	
	@PostMapping("savePatient")
	public String savePatient(@RequestBody SavePatientDto patient) {
		return ""+patientFacade.savePatient(patient);
	}
}
