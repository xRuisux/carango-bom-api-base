package br.com.caelum.carangobom.veiculo;

import java.util.Optional;

import br.com.caelum.carangobom.marca.Marca;
import br.com.caelum.carangobom.marca.MarcaRepository;

public class VeiculoForm {
	private Long marcaId;
	private String modelo;
	private Integer ano;
	private Double valor;
	
	public Veiculo converter(MarcaRepository marcaRepository) {
		Marca marca = marcaRepository.findById(this.marcaId)
		
		return
	}
}
