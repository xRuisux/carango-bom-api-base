package br.com.caelum.carangobom.validation;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParameterErrorOutputDto {

    private String parameter;
    private String message;
}