package com.br.desafio.picpay.backend.service;

import com.br.desafio.picpay.backend.domain.User;
import com.br.desafio.picpay.backend.exception.ErroSistemicoException;
import com.br.desafio.picpay.backend.mapper.UserMapper;
import com.br.desafio.picpay.backend.repository.UserRepository;
import jar.presentation.representation.CreatedUserResponse;
import jar.presentation.representation.UserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper mapper = new UserMapper();
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public CreatedUserResponse createUser(UserRequest body) {
        User user = mapper.mapFromRequestToUserDomain(body);
        try {
            userRepository.save(user);
            return mapper.mapFromDomainToResponse(user);
        } catch (Exception e) {
            throw new ErroSistemicoException("Erro ao salvar usuário", e);
        }
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ErroSistemicoException("Usuário não encontrado!"));
    }
}
