package com.omar.tickets.event.mappers;


import com.omar.tickets.event.domain.dtos.CreateEventRequest;
import com.omar.tickets.event.web.payload.request.CreateEventRequestDto;
import com.omar.tickets.event.web.payload.request.UpdateEventRequestDto;
import com.omar.tickets.event.domain.dtos.CreateTicketTypeRequest;
import com.omar.tickets.event.domain.dtos.UpdateEventRequest;
import com.omar.tickets.event.domain.model.Event;
import com.omar.tickets.event.web.payload.request.CreateTicketTypeRequestDto;
import com.omar.tickets.event.web.payload.response.*;
import com.omar.tickets.event.web.payload.response.UpdateTicketTypeResponseDto;
import com.omar.tickets.ticketType.domain.TicketType;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventMapper {

    CreateTicketTypeRequest fromDto(CreateTicketTypeRequestDto dto);

    CreateEventRequest fromDto(CreateEventRequestDto dto);

    CreateEventResponseDto toDto(Event event);

    ListEventTicketTypeResponseDto toDto(TicketType ticketType);

    ListEventResponseDto toListEventResponseDto(Event event);

    GetEventDetailsTicketTypesResponseDto toGetEventDetailsTicketTypesResponseDto(TicketType ticketType);

    GetEventDetailsResponseDto toGetEventDetailsResponseDto(Event event);

    UpdateEventRequest fromDto(UpdateEventRequestDto dto);

    UpdateTicketTypeResponseDto toUpdateTicketTypeResponseDto(TicketType ticketType);

    UpdateEventResponseDto toUpdateEventResponseDto(Event event);

    ListPublishedEventResponseDto toListPublishedEventResponseDto(Event event);

    GetPublishedEventDetailsTicketTypesResponseDto toGetPublishedEventDetailsTicketTypesResponseDto(TicketType ticketType);

    GetPublishedEventDetailsResponseDto toGetPublishedEventDetailsResponseDto(Event event);


}
