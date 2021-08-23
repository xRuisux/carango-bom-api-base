package br.com.caelum.carangobom.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExceptionMapper {
    private String error;
    private String hint;

}
