package br.com.caelum.carangobom.autenticacao;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class AutenticacaoDto {
    private String token;
    private String tipo;

    public AutenticacaoDto(String token, String tipo) {
        this.token = token;
        this.tipo = tipo;
    }
}
