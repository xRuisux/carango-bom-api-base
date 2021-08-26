package br.com.caelum.carangobom.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javassist.NotFoundException;

@RestControllerAdvice
public class AuthenticationExceptionHandler {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AuthenticationException.class)
    public ErrorMapper authenticationExceptionHandler(AuthenticationException exception) {
        return new ErrorMapper("Autenticação Inválida", "Verifique se o e-mail e senha foram informados corretamente");
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErroDto erroNotFoundException(NotFoundException exception) {
        return new ErroDto(exception.getMessage());
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ErroDto erroException(Exception exception) {
        return new ErroDto(exception.getMessage());
    }
}