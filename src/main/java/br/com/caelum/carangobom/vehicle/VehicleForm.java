package br.com.caelum.carangobom.vehicle;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.caelum.carangobom.marca.Marca;
import br.com.caelum.carangobom.marca.MarcaRepository;

public class VehicleForm {
	
	@NotNull
	private Long brandId;
	
	@NotNull @NotEmpty
	private String model;
	
	@NotNull @Min(1979)
	private Integer year;
	
	@NotNull @Min(1000)
	private Integer price;
	
	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Vehicle convert(Marca brand) {
	
		return new Vehicle(price, year, model, brand);
	}
	
	public Vehicle update(Vehicle vehicle, Marca brand) {
		
		vehicle.setBrand(brand);
		vehicle.setYear(year);
		vehicle.setModel(model);
		vehicle.setPrice(price);
		
		return vehicle;
	}
}
