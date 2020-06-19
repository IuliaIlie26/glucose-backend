package com.fils.glucose.exposition.facade;

import org.springframework.stereotype.Service;

import com.fils.glucose.application.service.investigation.CrudInvestigationService;
import com.fils.glucose.domain.investigations.InvestigationTicket;
import com.fils.glucose.exposition.dto.InvestigationTicketDto;
import com.fils.glucose.exposition.mappers.InvestigationMapperService;

@Service
public class InvestigationFacade {

	private final CrudInvestigationService crudInvestigationService;
	private final InvestigationMapperService investigationMapperService;

	public InvestigationFacade(CrudInvestigationService crudInvestigationService,
			InvestigationMapperService investigationMapperService) {
		this.crudInvestigationService = crudInvestigationService;
		this.investigationMapperService = investigationMapperService;
	}

	public void saveInvestigationTicket(InvestigationTicketDto dto) {
		InvestigationTicket bean = investigationMapperService.mapTicketToDomain(dto);
		crudInvestigationService.save(bean);

	}

	public InvestigationTicketDto getInvestigationTicket(String consultationId) {
		InvestigationTicket ticket = crudInvestigationService.getInvestigationTicket(consultationId);
		return investigationMapperService.mapTicketFromDomain(ticket);
	}

}
