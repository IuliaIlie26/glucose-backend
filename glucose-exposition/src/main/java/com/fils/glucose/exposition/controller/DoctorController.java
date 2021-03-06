package com.fils.glucose.exposition.controller;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fils.glucose.exposition.dto.DoctorDto;
import com.fils.glucose.exposition.dto.DoctorScheduleDto;
import com.fils.glucose.exposition.facade.DoctorFacade;

@RestController
@RequestMapping("api/doctor")
@CrossOrigin(origins = "http://localhost:2020")
public class DoctorController {

	private final DoctorFacade doctorFacade;

	public DoctorController(DoctorFacade doctorFacade) {
		this.doctorFacade = doctorFacade;
	}

	@GetMapping("getDoctorNameAndLastname")
	public String getDoctorNameAndLastname(@RequestParam Long id) {
		return doctorFacade.getDoctorNameAndLastname(id);
	}

	@GetMapping("getDoctorsList")
	public List<DoctorDto> getDoctorsList() {
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
	
	@PostMapping("saveSchedule")
	public void saveSchedule(@RequestBody DoctorScheduleDto schedule) {
		doctorFacade.saveSchedule(schedule);
	}
	
	@GetMapping("getDoctorNameAndLastnameByUsername")
	public String getDoctorNameAndLastnameByUsername(@RequestParam String username) {
		return doctorFacade.getDoctorNameAndLastnameByUsername(username);
	}
	
	@PostMapping("deleteDoctorById")
	public void deleteDoctorById(@RequestBody Long doctorId) {
		doctorFacade.deleteDoctorById(doctorId);
	}
}
