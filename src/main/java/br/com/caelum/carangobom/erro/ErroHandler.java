package br.com.caelum.carangobom.erro;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErroHandler {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AuthenticationException.class)
    public ErroDto erroAutenticaoHandler(AuthenticationException exception) {
        return new ErroDto("Autenticação Inválida", "Verifique se o e-mail e senha foram informados corretamente");
    }
}