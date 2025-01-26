package com.luciene.bffagendadortarefas.business.dto.in;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder //facilita a conversão dos dados
public class UsuarioDTORequest {

   private String nome;
   private String email;
   private String senha;
   private List<EnderecoDTORequest> enderecos;
   private List<TelefoneDTORequest> telefones;

}
