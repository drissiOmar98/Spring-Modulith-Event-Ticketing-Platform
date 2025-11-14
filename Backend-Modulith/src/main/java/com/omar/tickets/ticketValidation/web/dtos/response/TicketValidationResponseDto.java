package com.omar.tickets.ticketValidation.web.dtos.response;

import java.util.UUID;

import com.omar.tickets.ticketValidation.domain.TicketValidationStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketValidationResponseDto {
  private UUID ticketId;
  private TicketValidationStatusEnum status;
}
