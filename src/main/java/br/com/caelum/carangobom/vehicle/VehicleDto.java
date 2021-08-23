package br.com.caelum.carangobom.vehicle;

import java.util.List;
import java.util.stream.Collectors;

public class VehicleDto {
	
	private Long id;
	private String brandName;
	private Integer price;
	private Integer year;
	private String model;
	
	public VehicleDto(Vehicle veiculo) {
		this.id = veiculo.getId();
		this.year = veiculo.getYear();
		this.brandName = veiculo.getBrand().getNome();
		this.price = veiculo.getPrice();
		this.model = veiculo.getModel();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
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

	public List<VehicleDto> converter(List<Vehicle> veiculos) {
		return veiculos.stream().map(VehicleDto::new).collect(Collectors.toList());
	}
}
