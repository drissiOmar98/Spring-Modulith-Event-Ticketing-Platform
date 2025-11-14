package com.omar.tickets.qrCode.handler;

import com.omar.tickets.qrCode.exceptions.QrCodeGenerationException;
import com.omar.tickets.qrCode.exceptions.QrCodeNotFoundException;
import com.omar.tickets.shared.dtos.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class QrCodeExceptionHandler {

    @ExceptionHandler(QrCodeNotFoundException.class)
    public ResponseEntity<ErrorDto> handleQrCodeNotFoundException(QrCodeNotFoundException ex) {
        log.error("Caught QrCodeNotFoundException", ex);
        ErrorDto errorDto = new ErrorDto();
        errorDto.setError("QR code not found");
        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(QrCodeGenerationException.class)
    public ResponseEntity<ErrorDto> handleQrCodeGenerationException(QrCodeGenerationException ex) {
        log.error("Caught QrCodeGenerationException", ex);
        ErrorDto errorDto = new ErrorDto();
        errorDto.setError("Unable to generate QR Code");
        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
