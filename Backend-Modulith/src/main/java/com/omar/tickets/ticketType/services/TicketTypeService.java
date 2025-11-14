package com.omar.tickets.ticketType.services;


import com.omar.tickets.ticket.domain.Ticket;

import java.util.UUID;

public interface TicketTypeService {
  Ticket purchaseTicket(UUID userId, UUID ticketTypeId);
}
