package com.luciene.bffagendadortarefas.infrastructure.client;

import com.luciene.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "notificacao", url = "${notificacao.url}")
public interface EmailClient {

    void enviarEmail(@Valid @RequestBody TarefasDTOResponse dto);
}
