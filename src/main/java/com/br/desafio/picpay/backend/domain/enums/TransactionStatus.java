package com.br.desafio.picpay.backend.domain.enums;

import jar.presentation.representation.TransactionResponse;

public enum TransactionStatus {

    DONE(TransactionResponse.TransactionStatusEnum.DONE.getValue()),
    REJECTED(TransactionResponse.TransactionStatusEnum.REJECTED.getValue());

    private final String value;

    TransactionStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public TransactionStatus fromValue(String status) {
        for (TransactionStatus transactionStatus : TransactionStatus.values()) {
            if (transactionStatus.getValue().equals(status)) {
                return transactionStatus;
            }
        }
        return null;
    }
}
