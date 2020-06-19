package com.fils.glucose.exposition.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fils.glucose.exposition.dto.InvestigationTicketDto;
import com.fils.glucose.exposition.facade.InvestigationFacade;

@RestController
@RequestMapping("api/investigations")
@CrossOrigin(origins = "http://localhost:2020")
public class InvestigationsController {
	
	private final InvestigationFacade investigationFacade;

	public InvestigationsController(InvestigationFacade investigationFacade) {
		this.investigationFacade = investigationFacade;
	}
	
	@PostMapping("saveInvestigationTicket")
	public void saveInvestigationTicket(@RequestBody InvestigationTicketDto dto) {
		investigationFacade.saveInvestigationTicket(dto);
	}

	@GetMapping("getInvestigationTicket")
	public InvestigationTicketDto getInvestigationTicket(@RequestParam String consultationId) {
		return investigationFacade.getInvestigationTicket(consultationId);
	}
	
}
