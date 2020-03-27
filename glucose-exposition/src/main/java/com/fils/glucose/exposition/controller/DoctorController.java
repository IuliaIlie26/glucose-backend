package com.fils.glucose.exposition.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fils.glucose.exposition.dto.DoctorDto;
import com.fils.glucose.exposition.service.DoctorFacade;

@RestController
@RequestMapping("api/doctor")
@CrossOrigin(origins = "http://localhost:2020")
public class DoctorController {

	private final DoctorFacade doctorFacade;

	public DoctorController(DoctorFacade doctorFacade) {
		this.doctorFacade = doctorFacade;
	}

	@GetMapping("getDoctorByUsername")
	public DoctorDto getDoctorByUsername(@RequestParam String username) {
		return doctorFacade.getDoctorByUsername(username);
	}
}
