package com.luciene.bffagendadortarefas.infrastructure.exceptions;


public class BusinessException extends RuntimeException {

    public BusinessException(String mensagem) {
        super(mensagem);
    }

    public BusinessException(String mensagem, Throwable throwable) {
        super(mensagem);
    }

}


