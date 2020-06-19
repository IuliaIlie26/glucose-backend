package com.fils.glucose.domain.investigations;

import java.util.Optional;

public interface InvestigationTicketRepository {
	
	void save(InvestigationTicket ticket);

	Optional<InvestigationTicket> findByConsultationId(String consultationId);

}
