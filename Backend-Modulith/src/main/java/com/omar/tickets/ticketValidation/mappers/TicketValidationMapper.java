package com.omar.tickets.ticketValidation.mappers;


import com.omar.tickets.ticketValidation.web.dtos.response.TicketValidationResponseDto;
import com.omar.tickets.ticketValidation.domain.TicketValidation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TicketValidationMapper {

  @Mapping(target = "ticketId", source = "ticket.id")
  TicketValidationResponseDto toTicketValidationResponseDto(TicketValidation ticketValidation);

}
