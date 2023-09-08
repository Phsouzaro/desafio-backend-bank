package com.br.desafio.picpay.backend.mapper;

import com.br.desafio.picpay.backend.domain.Transaction;
import com.br.desafio.picpay.backend.domain.User;
import com.br.desafio.picpay.backend.domain.enums.TransactionStatus;
import jar.presentation.representation.TransactionResponse;

import java.math.BigDecimal;

public class TransactionMapper {
    public Transaction mapFromRequestToTransactionDomain(User payer, User payee, Long value) {
        Transaction transaction = new Transaction();
        transaction.setPayer(payer);
        transaction.setPayee(payee);
        transaction.setStatus(TransactionStatus.DONE);
        transaction.setValue(BigDecimal.valueOf(value));

        payer.addTransactionAsPayee(transaction);
        payee.addTransactionAsPayee(transaction);

        return transaction;
    }

    public TransactionResponse mapFromDomainToTransactionResponse(Transaction transaction) {
        TransactionResponse response = new TransactionResponse();
        response.setId(Math.toIntExact(transaction.getId()));
        response.setTransactionStatus(TransactionResponse.TransactionStatusEnum.fromValue(transaction.getStatus().getValue()));

        return response;
    }
}
