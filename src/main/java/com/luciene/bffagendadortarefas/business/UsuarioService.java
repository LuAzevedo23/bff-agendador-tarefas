package com.luciene.bffagendadortarefas.business;

import com.luciene.bffagendadortarefas.business.dto.in.EnderecoDTORequest;
import com.luciene.bffagendadortarefas.business.dto.in.LoginDTORequest;
import com.luciene.bffagendadortarefas.business.dto.in.TelefoneDTORequest;
import com.luciene.bffagendadortarefas.business.dto.in.UsuarioDTORequest;
import com.luciene.bffagendadortarefas.business.dto.out.EnderecoDTOResponse;
import com.luciene.bffagendadortarefas.business.dto.out.TelefoneDTOResponse;
import com.luciene.bffagendadortarefas.business.dto.out.UsuarioDTOResponse;
import com.luciene.bffagendadortarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient client;

    public UsuarioDTOResponse salvaUsuario(UsuarioDTORequest usuarioDTO) {

        return client.salvaUsuario(usuarioDTO);
    }

    public String loginUsuario(LoginDTORequest dto){

        return client.login(dto);
    }

    public UsuarioDTOResponse buscarUsuarioPorEmail(String email, String token) {

      return client.buscaUsuarioPorEmail(email, token);
    }

    public void deletaUsuarioPorEmail(String email, String token) {

        client.deletaUsuarioPorEmail(email, token);
    }

    public UsuarioDTOResponse atualizarDadosUsuario(String token, UsuarioDTORequest dto) {

        return client.atualizarDadoUsuario(dto, token);
    }

    public EnderecoDTOResponse atualizaEndereco(Long idEndereco, EnderecoDTORequest enderecoDTO, String token) {

        return client.atualizaEndereco(enderecoDTO,idEndereco, token);
    }

    public TelefoneDTOResponse atualizaTelefone(Long idTelefone, TelefoneDTORequest telefoneDTO, String token) {

        return client.atualizaTelefone(telefoneDTO, idTelefone, token);
    }

    //Esse metodo adiciona na Array de enderecos, mais enderecos por usuario, quantos eu quiser.
    public EnderecoDTOResponse cadastraEndereco(String token, EnderecoDTORequest dto) {

        return client.cadastraEndereco(dto, token);
    }

    //Esse metodo adiciona na Array de telefones, mais telefone por usuario, quantos eu quiser.
    public TelefoneDTOResponse cadastraTelefone(String token, TelefoneDTORequest dto) {

        return client.cadastraTelefone(dto, token);
    }
}


