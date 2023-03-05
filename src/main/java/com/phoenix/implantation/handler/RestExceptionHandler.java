package com.phoenix.implantation.handler;

import com.phoenix.implantation.exception.ExceptionDetails;
import com.phoenix.implantation.exception.InternalServerErrorException;
import com.phoenix.implantation.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDetails> handlerNotFoundException(NotFoundException notFoundException) {
        return handler(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.getReasonPhrase(), notFoundException.getMessage());
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<ExceptionDetails> handlerInternalServerErrorException(
            InternalServerErrorException internalServerErrorException) {
        return handler(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                internalServerErrorException.getMessage());
    }

    private ResponseEntity<ExceptionDetails> handler(HttpStatus status, String title, String message) {
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(status.value())
                        .title(title)
                        .message(message)
                        .build(), status
        );
    }

}
