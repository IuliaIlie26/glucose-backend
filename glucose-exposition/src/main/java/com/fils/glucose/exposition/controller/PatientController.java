package com.fils.glucose.exposition.controller;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fils.glucose.exposition.dto.PatientDto;
import com.fils.glucose.exposition.facade.PatientAlertsFacade;
import com.fils.glucose.exposition.facade.PatientFacade;

@RestController
@RequestMapping("api/patient")
@CrossOrigin(origins = "http://localhost:2020")
public class PatientController {

	private final PatientFacade patientFacade;
	private final PatientAlertsFacade alertsFacade;

	public PatientController(PatientFacade patientFacade, PatientAlertsFacade alertsFacade) {
		this.patientFacade = patientFacade;
		this.alertsFacade = alertsFacade;
	}

	@PostMapping("savePatient")
	public void savePatient(@RequestBody PatientDto patient) {
		patientFacade.savePatient(patient);
	}

	@GetMapping("getFullFormatAgeById")
	public String getFullFormatAgeById(@RequestParam Long id) {
		return patientFacade.getFullFormatAgeById(id);
	}

	@GetMapping("getAllPatients")
	public List<PatientDto> getAllPatients() {
		return patientFacade.getAllPatients();
	}

	@PostMapping("deletePatientById")
	public void deletePatientById(@RequestBody String id) {
		patientFacade.deletePatientById(id);
	}

	@GetMapping("getPatientById")
	public PatientDto getPatientById(@RequestParam Long id) {
		return patientFacade.getPatientById(id);
	}

	@GetMapping("getPatientNameByCnp")
	public String getPatientNameByCnp(@RequestParam String cnp) {
		return patientFacade.getPatientNameByCnp(cnp);
	}

	@PostMapping("updatePatient")
	public void updatePatient(@RequestBody PatientDto patientDto) {
		patientFacade.updatePatient(patientDto);
	}

	@GetMapping("getAllPatientAlertsForDoctor")
	public List<PatientDto> getAllPatientAlertsForDoctor(@RequestParam String username) {
		return alertsFacade.getAllPatientAlertsForDoctor(username);
	}
	
	@GetMapping("getAlertsListForPatient")
	public List<PatientAlertsDto> getAlertsListForPatient(@RequestParam Long patientId){
		return alertsFacade.getAlertsListForPatient(patientId);
	}
	
	@PostMapping("deleteAlertsForDoctor")
	public void deleteAlertsForDoctor(@RequestBody String username) {
		alertsFacade.deleteAlertsForDoctor(username);
	}

	@GetMapping("getPatientByEmail")
	public PatientDto getPatientByEmail(@RequestParam String username) {
		return patientFacade.getPatientByEmail(username);
	}
	
}
