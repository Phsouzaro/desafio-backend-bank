package com.br.desafio.picpay.backend.mapper;

import com.br.desafio.picpay.backend.domain.Transaction;
import com.br.desafio.picpay.backend.domain.User;
import com.br.desafio.picpay.backend.domain.enums.TransactionStatus;
import jar.presentation.representation.TransactionResponse;

public class TransactionMapper {
    public Transaction mapFromRequestToTransactionDomain(User payer, User payee) {
        Transaction transaction = new Transaction();
        transaction.setPayer(payer);
        transaction.setPayee(payee);
        transaction.setStatus(TransactionStatus.DONE);

        return transaction;
    }

    public TransactionResponse mapFromDomainToTransactionResponse(Transaction transaction) {
        TransactionResponse response = new TransactionResponse();
        response.setId(Math.toIntExact(transaction.getId()));
        response.setTransactionStatus(TransactionResponse.TransactionStatusEnum.fromValue(transaction.getStatus().getValue()));

        return response;
    }
}
