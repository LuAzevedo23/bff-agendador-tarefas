package com.luciene.bffagendadortarefas.business;

import com.luciene.bffagendadortarefas.business.dto.enums.StatusNotificacaoEnum;
import com.luciene.bffagendadortarefas.business.dto.in.LoginDTORequest;
import com.luciene.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j  //Essa anotação tem a função de ativar alguns métodos de log
public class CronService {

    private final TarefasService tarefasService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;

    @Value("${usuario.email}")
    private String email;

    @Value("${usuario.senha}")
    private String senha;

    @Scheduled(cron = "${cron.horario}")
    public void buscaTarefasProximaHora() {
        String token = login(converterParaRequestDTO());
        log.info("Iniciada a busca de tarefas");

        LocalDateTime horaFutura = LocalDateTime.now().plusHours(1);
        LocalDateTime horaFuturaMaisCinco = LocalDateTime.now().plusHours(1).plusMinutes(5);

        List<TarefasDTOResponse> listaTarefas =  tarefasService.buscaTarefasAgendadasPorPeriodo
                (horaFutura, horaFuturaMaisCinco, token);

        log.info("Tarefas encontradas " + listaTarefas);

    listaTarefas.forEach(tarefa -> { emailService.enviaEmail(tarefa);
        log.info("Email enviado para o usuário " + tarefa.getEmailUsuario());
    tarefasService.alteraStatus(StatusNotificacaoEnum.NOTIFICADO, tarefa.getId(),
            token);});
    log.info("Finalizada a busca e notificação de tarefas");
    }


    public String login(LoginDTORequest dto) {

        return usuarioService.loginUsuario(dto);
    }

    public LoginDTORequest converterParaRequestDTO(){
        return LoginDTORequest.builder()
                .email(email)
                .senha(senha)
                .build();
    }
}
