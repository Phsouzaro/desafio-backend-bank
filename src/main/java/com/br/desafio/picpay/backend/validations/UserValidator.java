package com.br.desafio.picpay.backend.validations;

import com.br.desafio.picpay.backend.domain.User;
import com.br.desafio.picpay.backend.domain.enums.UserType;
import com.br.desafio.picpay.backend.exception.ErroSistemicoException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserValidator {

    public static void validateUserType(User user) {
        if (user.getUserType().equals(UserType.PESSOA_JURIDICA)) {
            throw new ErroSistemicoException("REJEITADO: Transferência permitida somente para pessoa física");
        }
    }

    public static void validateUserBalance(User user, BigDecimal value) {
        if (user.getWallet().getBalance().compareTo(value) <= 0) {
            throw new ErroSistemicoException("REJEITADO: Usuário não possui saldo para transferencia");
        }
    }
}
