package br.com.caelum.carangobom.veiculo;

import java.util.List;
import java.util.stream.Collectors;

public class VeiculoDto {
	
	private Long id;
	private String nomeMarca;
	private Double valor;
	private Integer ano;
	private String modelo;
	
	public VeiculoDto(Veiculo veiculo) {
		this.id = veiculo.getId();
		this.ano = veiculo.getAno();
		this.nomeMarca = veiculo.getMarca().getNome();
		this.valor = veiculo.getValor();
		this.modelo = veiculo.getModelo();
	}
	
	public List<VeiculoDto> converter(List<Veiculo> veiculos) {
		return veiculos.stream().map(VeiculoDto::new).collect(Collectors.toList());
	}
}
