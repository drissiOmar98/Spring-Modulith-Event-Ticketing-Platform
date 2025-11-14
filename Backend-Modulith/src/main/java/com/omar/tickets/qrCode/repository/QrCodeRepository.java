package com.omar.tickets.qrCode.repository;

import java.util.Optional;
import java.util.UUID;

import com.omar.tickets.qrCode.domain.QrCode;
import com.omar.tickets.qrCode.domain.QrCodeStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QrCodeRepository extends JpaRepository<QrCode, UUID> {

    Optional<QrCode> findByTicketIdAndTicketPurchaserId(UUID ticketId, UUID ticketPurchaseId);

    Optional<QrCode> findByIdAndStatus(UUID id, QrCodeStatusEnum status);

}
