package br.com.caelum.carangobom.vehicle;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.caelum.carangobom.marca.Brand;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Vehicle {
	
	@Id @GeneratedValue(strategy = IDENTITY)
	private Long id;
	private Integer price;
	private Integer year;
	private String model;
	@ManyToOne
	private Brand brand;
	
	public Vehicle(Integer price, Integer year, String model, Brand brand) {
		this.price = price;
		this.year = year;
		this.model = model;
		this.brand = brand;
	}
}
