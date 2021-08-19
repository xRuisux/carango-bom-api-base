package br.com.caelum.carangobom.marca;

import lombok.Data;

@Data
public class MarcaForm {
    private String nome;

    public Marca convertToMarca() {
        return new Marca(nome);
    }
}
