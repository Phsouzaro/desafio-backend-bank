package com.br.desafio.picpay.backend.rest;

import jar.presentation.TransactionApi;
import jar.presentation.representation.TransactionRequest;
import jar.presentation.representation.TransactionResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class TransactionApiController implements TransactionApi {

    @Override
    public ResponseEntity<TransactionResponse> makeTransaction(TransactionRequest transactionRequest) {
        return TransactionApi.super.makeTransaction(transactionRequest);
    }
}
