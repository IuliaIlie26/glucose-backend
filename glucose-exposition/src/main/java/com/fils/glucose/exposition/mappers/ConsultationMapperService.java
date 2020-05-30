package com.fils.glucose.exposition.mappers;

import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.fils.glucose.application.service.doctor.CrudDoctorService;
import com.fils.glucose.application.service.patient.CrudPatientService;
import com.fils.glucose.domain.consultations.Consultation;
import com.fils.glucose.domain.personal.information.doctor.Doctor;
import com.fils.glucose.domain.personal.information.patient.Patient;
import com.fils.glucose.exposition.dto.ConsultationDto;

@Service
public class ConsultationMapperService {
	private final CrudDoctorService crudDoctorService;
	private final CrudPatientService crudPatientService;

	public ConsultationMapperService(CrudDoctorService crudDoctorService, CrudPatientService crudPatientService) {
		this.crudDoctorService = crudDoctorService;
		this.crudPatientService = crudPatientService;
	}

	public ConsultationDto mapFromDomain(Consultation bean) {
		ConsultationDto dto = new ConsultationDto();
		dto.doctorId = bean.getDoctorId();
		Patient patient = crudPatientService.getPatientById(bean.getPatientId());
		dto.patientCnp = patient.getCnp();
		dto.patientLastName = patient.getLastName();
		dto.patientName = patient.getFirstName();
		Doctor doc = crudDoctorService.findDoctorById(bean.getDoctorId());
		dto.doctorName = doc.getFirstName();
		dto.doctorLastName = doc.getLastName();
		dto.speciality = doc.getMedicalSpeciality();

		dto.date = bean.getConsultationDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

		return dto;
	}
}
