CREATE TABLE `risk_factors` (
  `patientId` bigint(11) NOT NULL,
  `height` int(3) NOT NULL,
  `weight` int(3) NOT NULL,
  `racial_origin` varchar(20) NOT NULL,
  `conception_method` varchar(20) NOT NULL,
  `family_history_diabetes` varchar(20) NOT NULL,
  `smoker` tinyint(4) NOT NULL,
  `macrosomic_baby` tinyint(4) NOT NULL,
  `previous_gmd` tinyint(4) NOT NULL,
  `overall_score` int(3) NOT NULL,
  PRIMARY KEY (`patientId`),
  CONSTRAINT `fk_risk_factors_1` FOREIGN KEY (`patientId`) REFERENCES `PATIENT` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
