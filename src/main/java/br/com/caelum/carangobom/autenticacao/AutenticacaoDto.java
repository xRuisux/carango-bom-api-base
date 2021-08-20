package br.com.caelum.carangobom.autenticacao;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AutenticacaoDto {
    private String token;
    private String tipo;

}
