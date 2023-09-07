package com.br.desafio.picpay.backend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.core.util.ObjectMapperFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@AllArgsConstructor
public class ServicoAutorizadorService {

    private final RestTemplate restTemplate = new RestTemplate();
    public boolean getAutorizacao() throws JsonProcessingException {
        String apiUrl = "https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6";

        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);

        JsonNode jsonNode = ObjectMapperFactory.buildStrictGenericObjectMapper().readTree(response.getBody());

        return jsonNode.get("message").asText().equals("Autorizado");
    }
}
