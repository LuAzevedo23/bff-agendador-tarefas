package com.luciene.bffagendadortarefas.business.dto.out;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder //facilita a conversão dos dados
public class UsuarioDTOResponse {

   private Long id;
   private String nome;
   private String email;
   private String senha;
   private List<EnderecoDTOResponse> enderecos;
   private List<TelefoneDTOResponse> telefones;

}
