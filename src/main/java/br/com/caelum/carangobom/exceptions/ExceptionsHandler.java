package br.com.caelum.carangobom.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

@RestControllerAdvice
public class ExceptionsHandler {

    @Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ParameterErrorOutputDto> erroHandler(MethodArgumentNotValidException exception) {
		List<ParameterErrorOutputDto> erroDto = new ArrayList<>();
		List<FieldError> errosFormulario = exception.getBindingResult().getFieldErrors();
		
		errosFormulario.forEach(e -> {
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ParameterErrorOutputDto erro = new ParameterErrorOutputDto(e.getField(), mensagem);
			erroDto.add(erro);
		});
		
		return erroDto;
	}

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AuthenticationException.class)
    public ErrorMapper authenticationExceptionHandler(AuthenticationException exception) {
        return new ErrorMapper("Autenticação Inválida", "Verifique se o e-mail e senha foram informados corretamente");
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorMapper> erroNotFoundException(ResponseStatusException exception) {
        System.out.println(exception.getMessage())
        return ResponseEntity.status(exception.getStatus()).body(new ErrorMapper(exception.getMessage()));
    }


    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorMapper erroException() {
        return new ErrorMapper("Houve uma falha ao processar esta requisição");
    }
}