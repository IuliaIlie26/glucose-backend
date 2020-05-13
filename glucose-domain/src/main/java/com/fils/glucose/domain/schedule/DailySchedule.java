package com.fils.glucose.domain.schedule;

import java.time.LocalTime;
import java.util.Optional;

public class DailySchedule {

	private LocalTime start;
	private LocalTime end;

	public DailySchedule() {
	}

	public DailySchedule(LocalTime start, LocalTime end) {
		super();
		this.start = start;
		this.end = end;
	}

	public Optional<LocalTime> getStart() {
		return Optional.ofNullable(start);
	}

	public void setStart(LocalTime start) {
		this.start = start;
	}

	public Optional<LocalTime> getEnd() {
		return Optional.ofNullable(end);
	}

	public void setEnd(LocalTime end) {
		this.end = end;
	}
}