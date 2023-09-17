package com.br.desafio.picpay.backend.exception;

public class ErroSistemicoException extends RuntimeException {

    public ErroSistemicoException(String message) {
        super(message);
    }

    public ErroSistemicoException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
