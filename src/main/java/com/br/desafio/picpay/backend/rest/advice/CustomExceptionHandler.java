package com.br.desafio.picpay.backend.rest.advice;

import com.br.desafio.picpay.backend.exception.ErroSistemicoException;
import jar.presentation.representation.Erro;
import jar.presentation.representation.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class CustomExceptionHandler {

    public static final String ERRO_SISTEMICO = "ERRO_SISTEMICO";

    @ExceptionHandler(ErroSistemicoException.class)
    public ResponseEntity<ErrorResponse> handleErroSistemicoException(Exception e) {

        var response = montaErroResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), ERRO_SISTEMICO, e.getCause());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    private ErrorResponse montaErroResponse(String message, Integer status, String description, Throwable cause) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(status);
        errorResponse.setDetailedMessage(message);
        errorResponse.setDescription(description);

        Erro erro = new Erro();
        erro.setError(cause.getMessage());
        erro.setField(cause.getLocalizedMessage());
        erro.setPropertyClass(erro.getPropertyClass());

        errorResponse.setErrors(List.of(erro));
        return errorResponse;
    }
}

