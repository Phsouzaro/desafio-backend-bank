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
            Transaction transaction = validateTransaction(body);
            boolean isTransacaoAutorizada = autorizadorService.getAutorizacao();

            if (isTransacaoAutorizada) updateUsersBalance(transaction);
            else transaction.setStatus(TransactionStatus.REJECTED);

            transactionRepository.save(transaction);
            return mapper.mapFromDomainToTransactionResponse(transaction);
        } catch (Exception e) {
            throw new ErroSistemicoException("Erro ao processar transação", e);
        }
    }

    private Transaction validateTransaction(TransactionRequest body){
        User payer = userService.getUserById(Long.valueOf(body.getPayer()));
        if (payer.getUserType().equals(UserType.PESSOA_JURIDICA)) {
            throw new ErroSistemicoException("REJEITADO: Transferência permitida somente para pessoa física");
        }
        if(payer.getAccount().getBalance().compareTo(BigDecimal.valueOf(body.getValue())) <= 0){
            throw new ErroSistemicoException("REJEITADO: Usuário não possui saldo para transferencia");
        }

        User payee = userService.getUserById(Long.valueOf(body.getPayee()));
        return mapper.mapFromRequestToTransactionDomain(payer, payee, body.getValue());
    }

    private void updateUsersBalance(Transaction transaction){
        BigDecimal payerBalance = transaction.getPayer().getAccount().getBalance();
        transaction.getPayer().getAccount().setBalance(
                payerBalance.subtract(transaction.getValue())
        );

        BigDecimal payeeBalance = transaction.getPayee().getAccount().getBalance();
        transaction.getPayee().getAccount().setBalance(
                payeeBalance.add(transaction.getValue())
        );
    }

}
