package br.com.caelum.carangobom.veiculo;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import br.com.caelum.carangobom.marca.Marca;

@Entity
public class Veiculo {
	
	@Id @GeneratedValue(strategy = IDENTITY)
	private Long id;
	private Double valor;
	private Integer ano;
	private String modelo;
	@OneToOne
	private Marca marca;
	
	public Long getId() {
		return id;
	}
	public Double getValor() {
		return valor;
	}
	public Integer getAno() {
		return ano;
	}
	public String getModelo() {
		return modelo;
	}
	public Marca getMarca() {
		return marca;
	}
}
