package com.fils.glucose.exposition.controller;

import java.util.Set;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fils.glucose.exposition.dto.DoctorDto;
import com.fils.glucose.exposition.dto.DoctorScheduleDto;
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
		return doctorFacade.findDoctorByUsername(username);
	}

	@GetMapping("getDoctorNameAndLastname")
	public String getDoctorNameAndLastname(@RequestParam String username) {
		return doctorFacade.getDoctorNameAndLastname(username);
	}

	@GetMapping("getDoctorsList")
	public Set<DoctorDto> getDoctorsList() {
		return doctorFacade.getDoctorsList();
	}

	@PostMapping("saveDoctor")
	public void saveDoctor(@RequestBody DoctorDto dto) {
		doctorFacade.saveDoctor(dto);
	}

	@PostMapping("updateDoctor")
	public void updateDoctor(@RequestBody DoctorDto dto) {
		doctorFacade.updateDoctor(dto);
	}
	
	@GetMapping("getScheduleForDoctor")
	public DoctorScheduleDto getScheduleForDoctor(@RequestParam Long id) {
		return doctorFacade.getScheduleForDoctor(id);
	}
}
