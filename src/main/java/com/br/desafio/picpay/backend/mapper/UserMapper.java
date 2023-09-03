package com.br.desafio.picpay.backend.mapper;

import com.br.desafio.picpay.backend.domain.User;
import com.br.desafio.picpay.backend.domain.enums.UserType;
import jar.presentation.representation.CreatedUserResponse;
import jar.presentation.representation.UserRequest;

public class UserMapper {

    public static final int TAMANHO_MAXIMO_CPF = 11;
    public User mapFromRequestToUserDomain(UserRequest request) {
        boolean isUserPessoaFisica = request.getCpfCnpj().length() == TAMANHO_MAXIMO_CPF;

        User domain = new User();
        domain.setFullName(request.getFullName());
        domain.setEmail(request.getEmail());
        domain.setPassword(request.getPassword());
        domain.setCpfCnpj(request.getCpfCnpj());
        domain.setUserType(isUserPessoaFisica ? UserType.PESSOA_FISICA : UserType.PESSOA_JURIDICA);

        return domain;
    }

    public CreatedUserResponse mapFromDomainToResponse(User user) {
        CreatedUserResponse response = new CreatedUserResponse();
        response.setId(user.getId());
        response.setName(user.getFullName());

        return response;
    }
}

