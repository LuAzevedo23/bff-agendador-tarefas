package com.luciene.bffagendadortarefas.infrastructure.client.config;

import com.luciene.bffagendadortarefas.infrastructure.exceptions.BusinessException;
import com.luciene.bffagendadortarefas.infrastructure.exceptions.ConflictException;
import com.luciene.bffagendadortarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.luciene.bffagendadortarefas.infrastructure.exceptions.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Slf4j
public class FeignError implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        String url = response.request().url();
        int status = response.status();
        String body = extractBody(response);

        // Log detalhado do erro
        log.error("Erro Feign ao acessar URL: {} | Status: {} | Corpo: {}", url, status, body);

        // Ignorar erros para o Swagger ou endpoints técnicos
        if (url.contains("/v3/api-docs") || url.contains("/swagger-ui")) {
            return new BusinessException("Erro técnico ao acessar endpoint OpenAPI");
        }

        // Tratar códigos HTTP específicos
        switch (status) {
            case 401:
                return new UnauthorizedException("Não autorizado: " + body);
            case 403:
                return new ResourceNotFoundException("Recurso não encontrado: " + body);
            case 404:
                return new ResourceNotFoundException("Recurso não encontrado na URL: " + url);
            case 409:
                return new ConflictException("Conflito de recurso: " + body);
            default:
                return new BusinessException("Erro desconhecido ao acessar URL: " + url + " | Status: " + status);
        }
    }
        /**
         * Extrai o corpo da resposta como String para fins de registro.
         */
        private String extractBody (Response response){
            try {
                if (response.body() == null) {
                    return "Sem corpo na resposta.";
                }
                return new BufferedReader(
                        new InputStreamReader(response.body().asInputStream(), StandardCharsets.UTF_8))
                        .lines()
                        .collect(Collectors.joining("\n"));
            } catch (Exception e) {
                log.error("Erro ao ler corpo da resposta Feign: {}", e.getMessage());
                return "Erro ao ler corpo da resposta.";
            }
        }
    }


