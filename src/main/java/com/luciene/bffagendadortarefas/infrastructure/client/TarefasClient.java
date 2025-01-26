package com.luciene.bffagendadortarefas.infrastructure.client;

import com.luciene.bffagendadortarefas.business.dto.enums.StatusNotificacaoEnum;
import com.luciene.bffagendadortarefas.business.dto.in.TarefasDTORequest;
import com.luciene.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@FeignClient(name = "agendador-tarefas", url = "${agendador-tarefas.url}")
public interface TarefasClient {

    @PostMapping
    TarefasDTOResponse gravarTarefas(@RequestBody TarefasDTORequest dto,
                             @RequestHeader(value="Authorization", required = false) String token);

    @GetMapping("/eventos")
    List<TarefasDTOResponse>buscaListaDeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader(value="Authorization") String token);

    @GetMapping
    List<TarefasDTOResponse> buscaTarefasPorEmail(@RequestHeader(value="Authorization", required = false) String token);

    @DeleteMapping
    void deletaTarefaPorId(@RequestParam("id") String id,
                           @RequestHeader(value="Authorization", required = false ) String token);

    @PatchMapping
    TarefasDTOResponse alteraStatusNotificacao(@RequestParam("status")StatusNotificacaoEnum status,
                                       @RequestHeader(value="Authorization", required = false) String token, String s);

    @PutMapping
    TarefasDTOResponse updateTarefas(@RequestBody TarefasDTORequest dto,
                                     @RequestParam("id") String id,
                                     @RequestHeader(value="Authorization", required = false) String token);

    }
