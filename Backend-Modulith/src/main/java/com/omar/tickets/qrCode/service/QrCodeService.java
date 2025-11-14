package com.omar.tickets.qrCode.service;


import com.omar.tickets.qrCode.domain.QrCode;
import com.omar.tickets.ticket.domain.Ticket;

import java.util.UUID;

public interface QrCodeService {

  QrCode generateQrCode(Ticket ticket);

  byte[] getQrCodeImageForUserAndTicket(UUID userId, UUID ticketId);

}
