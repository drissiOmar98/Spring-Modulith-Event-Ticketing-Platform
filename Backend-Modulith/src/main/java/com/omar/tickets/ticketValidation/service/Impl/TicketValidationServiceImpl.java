package com.omar.tickets.ticketValidation.service.Impl;


import com.omar.tickets.qrCode.domain.QrCode;
import com.omar.tickets.qrCode.domain.QrCodeStatusEnum;
import com.omar.tickets.qrCode.exceptions.QrCodeNotFoundException;
import com.omar.tickets.qrCode.repository.QrCodeRepository;
import com.omar.tickets.ticket.domain.Ticket;
import com.omar.tickets.ticket.repository.TicketRepository;
import com.omar.tickets.ticketValidation.domain.TicketValidation;
import com.omar.tickets.ticketValidation.domain.TicketValidationMethod;
import com.omar.tickets.ticketValidation.domain.TicketValidationStatusEnum;
import com.omar.tickets.ticketValidation.repository.TicketValidationRepository;
import com.omar.tickets.ticketValidation.service.TicketValidationService;
import com.omar.tickets.ticket.exceptions.TicketNotFoundException;
import jakarta.transaction.Transactional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class TicketValidationServiceImpl implements TicketValidationService {

  private final QrCodeRepository qrCodeRepository;
  private final TicketValidationRepository ticketValidationRepository;
  private final TicketRepository ticketRepository;

  @Override
  public TicketValidation validateTicketByQrCode(UUID qrCodeId) {
    QrCode qrCode = qrCodeRepository.findByIdAndStatus(qrCodeId, QrCodeStatusEnum.ACTIVE)
        .orElseThrow(() -> new QrCodeNotFoundException(
            String.format(
                "QR Code with ID %s was not found", qrCodeId
            )
        ));

    Ticket ticket = qrCode.getTicket();

    return validateTicket(ticket, TicketValidationMethod.QR_SCAN);
  }

  private TicketValidation validateTicket(Ticket ticket,
      TicketValidationMethod ticketValidationMethod) {
    TicketValidation ticketValidation = new TicketValidation();
    ticketValidation.setTicket(ticket);
    ticketValidation.setValidationMethod(ticketValidationMethod);

    TicketValidationStatusEnum ticketValidationStatus = ticket.getValidations().stream()
        .filter(v -> TicketValidationStatusEnum.VALID.equals(v.getStatus()))
        .findFirst()
        .map(v -> TicketValidationStatusEnum.INVALID)
        .orElse(TicketValidationStatusEnum.VALID);

    ticketValidation.setStatus(ticketValidationStatus);

    return ticketValidationRepository.save(ticketValidation);
  }

  @Override
  public TicketValidation validateTicketManually(UUID ticketId) {
    Ticket ticket = ticketRepository.findById(ticketId)
        .orElseThrow(TicketNotFoundException::new);
    return validateTicket(ticket, TicketValidationMethod.MANUAL);
  }
}
