package br.com.caelum.carangobom.marca;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MarcaMapper {
    private String nome;
    private Long id;

    public MarcaMapper(Marca marca) {
        this.nome = marca.getNome();
        this.id = marca.getId();
    }
    
	public static List<MarcaMapper> converter(List<Marca> marcas) {
		return marcas.stream().map(MarcaMapper::new).collect(Collectors.toList());
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
