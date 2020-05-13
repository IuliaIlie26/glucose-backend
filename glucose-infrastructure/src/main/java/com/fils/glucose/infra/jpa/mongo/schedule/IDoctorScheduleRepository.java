package com.fils.glucose.infra.jpa.mongo.schedule;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fils.glucose.domain.schedule.DoctorSchedule;

public interface IDoctorScheduleRepository extends MongoRepository<DoctorSchedule, Long>{

	Optional<DoctorSchedule> findById(Long id);
}
