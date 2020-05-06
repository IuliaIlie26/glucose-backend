package com.fils.glucose.domain.personal.information.doctor;

import java.time.LocalTime;

public class DailySchedule {

	private LocalTime start;
	private LocalTime end;

	public LocalTime getStart() {
		return start;
	}

	public void setStart(LocalTime start) {
		this.start = start;
	}

	public LocalTime getEnd() {
		return end;
	}

	public void setEnd(LocalTime end) {
		this.end = end;
	}
}
