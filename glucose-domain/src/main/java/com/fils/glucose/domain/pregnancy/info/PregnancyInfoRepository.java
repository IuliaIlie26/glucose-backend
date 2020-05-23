package com.fils.glucose.domain.pregnancy.info;

import java.util.Optional;

public interface PregnancyInfoRepository {

	void save(PregnancyInfo info);

	Optional<PregnancyInfo> findByPatientId(Long id);
}
