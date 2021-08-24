package br.com.caelum.carangobom.vehicle;

import java.util.List;
import java.util.stream.Collectors;

public class VehicleMapper {
	
	private Long id;
	private String brandName;
	private Integer price;
	private Integer year;
	private String model;
	
	public VehicleMapper(Vehicle vehicle) {
		this.id = vehicle.getId();
		this.year = vehicle.getYear();
		this.brandName = vehicle.getBrand().getNome();
		this.price = vehicle.getPrice();
		this.model = vehicle.getModel();
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

	public List<VehicleMapper> converter(List<Vehicle> vehicles) {
		return vehicles.stream().map(VehicleMapper::new).collect(Collectors.toList());
	}
}
