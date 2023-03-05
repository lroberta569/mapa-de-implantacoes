package com.phoenix.implantation.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Salatiel Fiore
 * @email salatiel.fiore@arphoenix.com.br
 * @description  Classe de excessão de erro interno
 */
@Data
@Builder
public class ExceptionDetails {

    protected String title;
    protected int status;
    protected String message;
    protected LocalDateTime timestamp;
}
