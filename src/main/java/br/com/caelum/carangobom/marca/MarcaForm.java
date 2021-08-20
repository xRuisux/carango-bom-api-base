package br.com.caelum.carangobom.marca;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MarcaForm {
    private String nome;

    public Marca convertToMarca() {
        return new Marca(nome);
    }
}
