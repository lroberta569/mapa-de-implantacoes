package com.phoenix.implantation.exception;

/**
 * @author Larissa Silva
 * @email larissa.silva@arphoenix.com.br
 * @description Classe de excessão de erro interno
 */
public class InternalServerErrorException extends RuntimeException {
    public InternalServerErrorException(String message) {
        super(message);
    }
}
