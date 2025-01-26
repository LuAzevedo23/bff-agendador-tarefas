package com.luciene.bffagendadortarefas.infrastructure.client;

import com.luciene.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "notificacao", url = "${notificacao.url}")
public interface EmailClient {

    @PostMapping("/enviar")
    void enviarEmail(@Valid @RequestBody TarefasDTOResponse dto);
}

//@FeignClient(name = "emailClient", url = //"${email.client.base-url}")
//public interface EmailClient {
//    @PostMapping("/send")
//   void sendEmail(@RequestBody EmailRequest //emailRequest);
//}