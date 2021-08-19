package br.com.caelum.carangobom.marca;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class MarcaMapper {
    private String nome;
    private Long id;

    public MarcaMapper(Marca marca) {
        this.nome = marca.getNome();
        this.id = marca.getId();
    }
}
