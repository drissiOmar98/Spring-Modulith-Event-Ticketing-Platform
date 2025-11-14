package com.omar.tickets.ticketType.web.handler;

import com.omar.tickets.shared.dtos.ErrorDto;
import com.omar.tickets.ticketType.exceptions.TicketTypeNotFoundException;
import com.omar.tickets.ticketType.exceptions.TicketsSoldOutException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class TicketTypeExceptionHandler {


    @ExceptionHandler(TicketsSoldOutException.class)
    public ResponseEntity<ErrorDto> handleTicketsSoldOutException(TicketsSoldOutException ex) {
        log.error("Caught TicketsSoldOutException", ex);
        ErrorDto errorDto = new ErrorDto();
        errorDto.setError("Tickets are sold out for this ticket type");
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TicketTypeNotFoundException.class)
    public ResponseEntity<ErrorDto> handleTicketTypeNotFoundException(TicketTypeNotFoundException ex) {
        log.error("Caught TicketTypeNotFoundException", ex);
        ErrorDto errorDto = new ErrorDto();
        errorDto.setError("Ticket type not found");
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

}
