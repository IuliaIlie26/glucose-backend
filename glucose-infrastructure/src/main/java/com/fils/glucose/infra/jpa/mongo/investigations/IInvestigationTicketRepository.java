package com.fils.glucose.infra.jpa.mongo.investigations;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fils.glucose.domain.investigations.InvestigationTicket;

public interface IInvestigationTicketRepository extends MongoRepository<InvestigationTicket, String>{

	Optional<InvestigationTicket> findByConsultationId(String consultationId);

}
