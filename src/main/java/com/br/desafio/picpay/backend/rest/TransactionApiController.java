package com.br.desafio.picpay.backend.rest;

import com.br.desafio.picpay.backend.service.TransactionService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jar.presentation.TransactionApi;
import jar.presentation.representation.TransactionRequest;
import jar.presentation.representation.TransactionResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class TransactionApiController implements TransactionApi {

    @Autowired
    private TransactionService service;
    private final Gson gson = new GsonBuilder().create();

    @Override
    public ResponseEntity<TransactionResponse> makeTransaction(TransactionRequest transactionRequest) {
        log.info("### Iniciando serviço de transações com a request: {}", gson.toJson(transactionRequest));
        TransactionResponse response = service.doTransaction(transactionRequest);
        log.info("### Iniciando serviço de transações com a request: {}", gson.toJson(transactionRequest));
        return ResponseEntity.ok(response);
    }
}
