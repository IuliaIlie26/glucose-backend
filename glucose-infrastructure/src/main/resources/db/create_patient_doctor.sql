CREATE TABLE `patient_doctor` (
  `patient_id` bigint(11) NOT NULL,
  `doctor_id` bigint(11) NOT NULL,
  PRIMARY KEY (`patient_id`,`doctor_id`),
  KEY `fk_patient_doctor_1_idx` (`doctor_id`),
  CONSTRAINT `fk_patient_doctor_1` FOREIGN KEY (`doctor_id`) REFERENCES `DOCTOR` (`id`),
  CONSTRAINT `fk_patient_doctor_2` FOREIGN KEY (`patient_id`) REFERENCES `PATIENT` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
