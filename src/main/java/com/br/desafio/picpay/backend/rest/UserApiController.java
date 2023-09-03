package com.br.desafio.picpay.backend.rest;

import jar.presentation.UserApi;
import jar.presentation.representation.CreatedUserResponse;
import jar.presentation.representation.UserRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class UserApiController implements UserApi {
    @Override
    public ResponseEntity<CreatedUserResponse> createUser(UserRequest userRequest) {

        log.info("Entrou na controller");
        return ResponseEntity.ok().build();
    }
}
