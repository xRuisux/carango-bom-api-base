package br.com.caelum.carangobom.vehicle;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import br.com.caelum.carangobom.marca.Marca;

@Entity
public class Vehicle {
	
	@Id @GeneratedValue(strategy = IDENTITY)
	private Long id;
	private Integer price;
	private Integer year;
	private String model;
	@OneToOne
	private Marca brand;
	
	public Vehicle() {		
	}

	public Vehicle(Integer price, Integer year, String model, Marca brand) {
		this.price = price;
		this.year = year;
		this.model = model;
		this.brand = brand;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Marca getBrand() {
		return brand;
	}

	public void setBrand(Marca brand) {
		this.brand = brand;
	}
	

}
