package com.luciene.bffagendadortarefas.infrastructure.client;

import com.luciene.bffagendadortarefas.business.dto.in.EnderecoDTORequest;
import com.luciene.bffagendadortarefas.business.dto.in.LoginDTORequest;
import com.luciene.bffagendadortarefas.business.dto.in.TelefoneDTORequest;
import com.luciene.bffagendadortarefas.business.dto.in.UsuarioDTORequest;
import com.luciene.bffagendadortarefas.business.dto.out.EnderecoDTOResponse;
import com.luciene.bffagendadortarefas.business.dto.out.TelefoneDTOResponse;
import com.luciene.bffagendadortarefas.business.dto.out.UsuarioDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping
    UsuarioDTOResponse buscaUsuarioPorEmail(@RequestParam("email") String email,
                                            @RequestHeader(value="Authorization") String token);
    @PostMapping
    UsuarioDTOResponse salvaUsuario(@RequestBody UsuarioDTORequest usuarioDtO);

    @PostMapping("/login")
    String login(@RequestBody LoginDTORequest usuarioDTO);

    @DeleteMapping("/{email}")
    void deletaUsuarioPorEmail(@PathVariable String email,
                               @RequestHeader(value="Authorization") String token);

    @PutMapping
    UsuarioDTOResponse atualizarDadoUsuario(@RequestBody UsuarioDTORequest dto,
                                            @RequestHeader(value="Authorization") String token);

    //Metodo responsavel por atualizar endereços, exemplo n. rua errado, vai la no jason e atualiza...
    @PutMapping("/endereco")
    EnderecoDTOResponse atualizaEndereco(@RequestBody EnderecoDTORequest dto,
                                         @RequestParam("id") Long id,
                                         @RequestHeader(value="Authorization") String token);

    @PutMapping("/telefone")
    TelefoneDTOResponse atualizaTelefone(@RequestBody TelefoneDTORequest dto,
                                         @RequestParam("id") Long id,
                                         @RequestHeader(value="Authorization") String token);

    @PostMapping("/endereco")
    EnderecoDTOResponse cadastraEndereco(@RequestBody EnderecoDTORequest dto,
                                         @RequestHeader(value="Authorization") String token);

    @PostMapping("/telefone")
    TelefoneDTOResponse cadastraTelefone(@RequestBody TelefoneDTORequest dto,
                                         @RequestHeader(value="Authorization") String token);
}



