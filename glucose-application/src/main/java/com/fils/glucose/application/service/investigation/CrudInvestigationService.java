package com.fils.glucose.application.service.investigation;

import org.springframework.stereotype.Service;

import com.fils.glucose.domain.investigations.InvestigationTicket;
import com.fils.glucose.domain.investigations.InvestigationTicketRepository;

@Service
public class CrudInvestigationService {

	private final InvestigationTicketRepository investigationTicketRepo;

	public CrudInvestigationService(InvestigationTicketRepository investigationTicketRepo) {
		this.investigationTicketRepo = investigationTicketRepo;
	}

	public void save(InvestigationTicket ticket) {
		investigationTicketRepo.save(ticket);
	}

	public InvestigationTicket getInvestigationTicket(String consultationId) {
		return investigationTicketRepo.findByConsultationId(consultationId).orElse(new InvestigationTicket());
	}

}
