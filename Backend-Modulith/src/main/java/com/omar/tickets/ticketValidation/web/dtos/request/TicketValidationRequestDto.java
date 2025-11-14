package com.omar.tickets.ticketValidation.web.dtos.request;


import java.util.UUID;

import com.omar.tickets.ticketValidation.domain.TicketValidationMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketValidationRequestDto {
  private UUID id;
  private TicketValidationMethod method;
}
