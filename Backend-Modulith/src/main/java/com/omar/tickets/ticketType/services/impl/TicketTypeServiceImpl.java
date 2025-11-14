package com.omar.tickets.ticketType.services.impl;

import com.omar.tickets.ticket.domain.Ticket;
import com.omar.tickets.ticket.domain.TicketStatusEnum;
import com.omar.tickets.ticketType.domain.TicketType;
import com.omar.tickets.user.domain.User;
import com.omar.tickets.ticketType.exceptions.TicketTypeNotFoundException;
import com.omar.tickets.ticketType.exceptions.TicketsSoldOutException;
import com.omar.tickets.user.exceptions.UserNotFoundException;
import com.omar.tickets.ticket.repository.TicketRepository;
import com.omar.tickets.ticketType.repository.TicketTypeRepository;
import com.omar.tickets.user.repository.UserRepository;
import com.omar.tickets.qrCode.service.QrCodeService;
import com.omar.tickets.ticketType.services.TicketTypeService;
import jakarta.transaction.Transactional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketTypeServiceImpl implements TicketTypeService {

  private final UserRepository userRepository;
  private final TicketTypeRepository ticketTypeRepository;
  private final TicketRepository ticketRepository;
  private final QrCodeService qrCodeService;

  @Override
  @Transactional
  public Ticket purchaseTicket(UUID userId, UUID ticketTypeId) {
    User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(
        String.format("User with ID %s was not found", userId)
    ));

    TicketType ticketType = ticketTypeRepository.findByIdWithLock(ticketTypeId)
        .orElseThrow(() -> new TicketTypeNotFoundException(
            String.format("Ticket type with ID %s was not found", ticketTypeId)
        ));

    int purchasedTickets = ticketRepository.countByTicketTypeId(ticketType.getId());
    Integer totalAvailable = ticketType.getTotalAvailable();

    if(purchasedTickets + 1 > totalAvailable) {
      throw new TicketsSoldOutException();
    }

    Ticket ticket = new Ticket();
    ticket.setStatus(TicketStatusEnum.PURCHASED);
    ticket.setTicketType(ticketType);
    ticket.setPurchaser(user);

    Ticket savedTicket = ticketRepository.save(ticket);
    qrCodeService.generateQrCode(savedTicket);

    return ticketRepository.save(savedTicket);
  }
}
