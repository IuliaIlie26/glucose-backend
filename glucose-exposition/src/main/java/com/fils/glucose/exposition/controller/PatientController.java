package com.fils.glucose.exposition.controller;

import java.util.Set;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fils.glucose.exposition.dto.PatientDto;
import com.fils.glucose.exposition.dto.RiskFactorsDto;
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
	public String savePatient(@RequestBody PatientDto patient) {
		return patientFacade.savePatient(patient).toString();
	}
	
	@PostMapping("saveRiskFactors")
	public void saveRiskFactors(@RequestBody RiskFactorsDto riskFactors) {
		patientFacade.saveRiskFactors(riskFactors);
	}
	
	@GetMapping("getFullFormatAgeById")
	public String getFullFormatAgeById(@RequestParam Long id) {
		return patientFacade.getFullFormatAgeById(id);
	}
	
	@GetMapping("getAllPatients")
	public Set<PatientDto> getAllPatients() {
		return patientFacade.getAllPatients();
	}
	
	@PostMapping("deletePatientById")
	public void deletePatientById(@RequestBody String id) {
		patientFacade.deletePatientById(id);
	}
}
