package com.br.desafio.picpay.backend.service;

import jar.presentation.representation.CreatedUserResponse;
import jar.presentation.representation.UserRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public CreatedUserResponse createUser(UserRequest body) {


        return new CreatedUserResponse();
    }
}
