package br.com.caelum.carangobom.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthenticationExceptionHandler {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AuthenticationException.class)
    public ExceptionMapper exceptionHandler() {
        return new ExceptionMapper("Autenticação Inválida", "Verifique se o e-mail e senha foram informados corretamente");
    }
}