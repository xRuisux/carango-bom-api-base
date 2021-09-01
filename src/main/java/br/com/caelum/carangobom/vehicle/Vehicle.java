package br.com.caelum.carangobom.vehicle;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.caelum.carangobom.brand.Brand;

@Entity
public class Vehicle {
	
	@Id @GeneratedValue(strategy = IDENTITY)
	private Long id;
	private Integer price;
	private Integer year;
	private String model;
	@ManyToOne
  	private Brand brand;

	public Vehicle() {}

	public Vehicle(Integer price, Integer year, String model, Brand brand) {
		super();
		this.price = price;
		this.year = year;
		this.model = model;
		this.brand = brand;
	}

	public Long getId() {
		return id;
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
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
}
