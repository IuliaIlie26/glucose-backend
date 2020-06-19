package com.fils.glucose.exposition.mappers;

import org.springframework.stereotype.Service;

import com.fils.glucose.domain.investigations.InvestigationTicket;
import com.fils.glucose.exposition.dto.InvestigationTicketDto;

@Service
public class InvestigationMapperService {

	public InvestigationTicket mapTicketToDomain(InvestigationTicketDto dto) {
		InvestigationTicket bean = new InvestigationTicket();
		bean.setConsultationId(dto.consultationId);
		bean.setInvestigations(dto.investigationsList);
		return bean;
	}

	public InvestigationTicketDto mapTicketFromDomain(InvestigationTicket bean) {
		InvestigationTicketDto dto = new InvestigationTicketDto();
		dto.consultationId = bean.getConsultationId();
		dto.investigationsList = bean.getInvestigations();
		return dto;
	}

}
