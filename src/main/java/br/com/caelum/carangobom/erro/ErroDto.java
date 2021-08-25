package br.com.caelum.carangobom.erro;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErroDto {
    private String erro;
    private String hint;

    public ErroDto(String erro) {
        this.erro = erro;
    }
}
