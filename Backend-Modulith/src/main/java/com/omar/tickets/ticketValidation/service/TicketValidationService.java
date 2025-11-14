package com.omar.tickets.ticketValidation.service;


import com.omar.tickets.ticketValidation.domain.TicketValidation;

import java.util.UUID;

public interface TicketValidationService {
  TicketValidation validateTicketByQrCode(UUID qrCodeId);
  TicketValidation validateTicketManually(UUID ticketId);
}
