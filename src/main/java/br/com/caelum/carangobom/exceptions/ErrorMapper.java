package br.com.caelum.carangobom.exceptions;

import lombok.Getter;

@Getter
public class ErrorMapper {
    private String error;
    private String hint;

    public ErrorMapper(String error) {
        this.error = error;
    }

    public ErrorMapper(String error, String hint) {
        this.error = error;
        this.hint = hint;
    }
}
