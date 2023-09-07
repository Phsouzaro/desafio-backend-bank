package com.br.desafio.picpay.backend.service;

import com.br.desafio.picpay.backend.domain.Transaction;
import com.br.desafio.picpay.backend.domain.User;
import com.br.desafio.picpay.backend.domain.enums.TransactionStatus;
import com.br.desafio.picpay.backend.domain.enums.UserType;
import com.br.desafio.picpay.backend.exception.ErroSistemicoException;
import com.br.desafio.picpay.backend.mapper.TransactionMapper;
import com.br.desafio.picpay.backend.repository.TransactionRepository;
import jar.presentation.representation.TransactionRequest;
import jar.presentation.representation.TransactionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ServicoAutorizadorService autorizadorService;
    private final TransactionMapper mapper = new TransactionMapper();

    @Transactional
    public TransactionResponse doTransaction(TransactionRequest body) {
        try {
            User payer = userService.getUserById(Long.valueOf(body.getPayer()));
            validatePayer(payer);
            validateIfPayerHasFunds(payer, body.getValue());
            User payee = userService.getUserById(Long.valueOf(body.getPayee()));

            Transaction transaction = mapper.mapFromRequestToTransactionDomain(payer, payee);

            payee.addTransactionAsPayee(transaction);
            payer.addTransactionAsPayer(transaction);
            boolean isAutorizado = autorizadorService.getAutorizacao();

            if (!isAutorizado) {
                transaction.setStatus(TransactionStatus.REJECTED);
            }

            if(transaction.getStatus().equals(TransactionStatus.DONE)){
                //aqui vai atualizar o saldo dos usuarios
            }
            transactionRepository.save(transaction);

            return mapper.mapFromDomainToTransactionResponse(transaction);
        } catch (Exception e) {
            throw new ErroSistemicoException("Erro ao processar transação", e);
        }
    }

    private void validateIfPayerHasFunds(User payer, Long value) {
        if(payer.getAccount().getBalance().compareTo(BigDecimal.valueOf(value)) <= 0){
            throw new ErroSistemicoException("REJEITADO: Usuário não possui saldo para transferencia");
        }
    }

    private void validatePayer(User payer) {
        if (payer.getUserType().equals(UserType.PESSOA_JURIDICA)) {
            throw new ErroSistemicoException("REJEITADO: Transferência permitida somente para pessoa física");
        }
    }
}
