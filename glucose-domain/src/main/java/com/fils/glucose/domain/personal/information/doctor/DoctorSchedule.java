package com.fils.glucose.domain.personal.information.doctor;

import java.time.LocalTime;

import javax.validation.constraints.NotNull;

public class DoctorSchedule {

	@NotNull
	private Long doctorId;

	private LocalTime mondayStart;
	private LocalTime mondayEnd;

	private LocalTime tuesdayStart;
	private LocalTime tuesdayEnd;

	private LocalTime wednesdayStart;
	private LocalTime wednesdayEnd;

	private LocalTime thursdayStart;
	private LocalTime thursdayEnd;

	private LocalTime fridayStart;
	private LocalTime fridayEnd;

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public LocalTime getMondayStart() {
		return mondayStart;
	}

	public void setMondayStart(LocalTime mondayStart) {
		this.mondayStart = mondayStart;
	}

	public LocalTime getMondayEnd() {
		return mondayEnd;
	}

	public void setMondayEnd(LocalTime mondayEnd) {
		this.mondayEnd = mondayEnd;
	}

	public LocalTime getTuesdayStart() {
		return tuesdayStart;
	}

	public void setTuesdayStart(LocalTime tuesdayStart) {
		this.tuesdayStart = tuesdayStart;
	}

	public LocalTime getTuesdayEnd() {
		return tuesdayEnd;
	}

	public void setTuesdayEnd(LocalTime tuesdayEnd) {
		this.tuesdayEnd = tuesdayEnd;
	}

	public LocalTime getWednesdayStart() {
		return wednesdayStart;
	}

	public void setWednesdayStart(LocalTime wednesdayStart) {
		this.wednesdayStart = wednesdayStart;
	}

	public LocalTime getWednesdayEnd() {
		return wednesdayEnd;
	}

	public void setWednesdayEnd(LocalTime wednesdayEnd) {
		this.wednesdayEnd = wednesdayEnd;
	}

	public LocalTime getThursdayStart() {
		return thursdayStart;
	}

	public void setThursdayStart(LocalTime thursdayStart) {
		this.thursdayStart = thursdayStart;
	}

	public LocalTime getThursdayEnd() {
		return thursdayEnd;
	}

	public void setThursdayEnd(LocalTime thursdayEnd) {
		this.thursdayEnd = thursdayEnd;
	}

	public LocalTime getFridayStart() {
		return fridayStart;
	}

	public void setFridayStart(LocalTime fridayStart) {
		this.fridayStart = fridayStart;
	}

	public LocalTime getFridayEnd() {
		return fridayEnd;
	}

	public void setFridayEnd(LocalTime fridayEnd) {
		this.fridayEnd = fridayEnd;
	}
}
