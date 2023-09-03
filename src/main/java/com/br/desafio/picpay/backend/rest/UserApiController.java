package com.br.desafio.picpay.backend.rest;

import com.br.desafio.picpay.backend.service.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jar.presentation.UserApi;
import jar.presentation.representation.CreatedUserResponse;
import jar.presentation.representation.UserRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class UserApiController implements UserApi {

    @Autowired
    private UserService userService;

    private final Gson gson = new GsonBuilder().create();

    @Override
    public ResponseEntity<CreatedUserResponse> createUser(UserRequest userRequest) {
        log.info("### Iniciando serviço de cadastro de usuário com a request: {}", gson.toJson(userRequest));
        CreatedUserResponse response = userService.createUser(userRequest);
        log.info("### Finalizado serviço de cadastro de usuário com o response: {}", gson.toJson(response));
        return ResponseEntity.ok(response);
    }
}
