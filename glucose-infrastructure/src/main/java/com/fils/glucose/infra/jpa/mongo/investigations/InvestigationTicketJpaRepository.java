package com.fils.glucose.infra.jpa.mongo.investigations;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.fils.glucose.domain.investigations.InvestigationTicket;
import com.fils.glucose.domain.investigations.InvestigationTicketRepository;

@Repository
public class InvestigationTicketJpaRepository implements InvestigationTicketRepository {

	private final IInvestigationTicketRepository investigationRepo;

	public InvestigationTicketJpaRepository(IInvestigationTicketRepository investigationRepo) {
		this.investigationRepo = investigationRepo;
	}

	@Override
	public void save(InvestigationTicket ticket) {
		investigationRepo.save(ticket);
	}

	@Override
	public Optional<InvestigationTicket> findByConsultationId(String consultationId) {
		return investigationRepo.findByConsultationId(consultationId);
	}

}
