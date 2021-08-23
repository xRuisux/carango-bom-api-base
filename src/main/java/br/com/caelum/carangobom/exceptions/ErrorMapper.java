package br.com.caelum.carangobom.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorMapper {
    private String error;
    private String hint;

}
