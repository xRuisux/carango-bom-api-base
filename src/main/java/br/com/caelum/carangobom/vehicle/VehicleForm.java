package br.com.caelum.carangobom.vehicle;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.caelum.carangobom.marca.Brand;
import lombok.Getter;


class VehicleForm {
	
		@NotNull
		@Getter
		private Long brandId;
		
		@NotNull @NotEmpty
		private String model;
		
		@NotNull @Min(1979)
		private Integer year;
		
		@NotNull @Min(1000)
		private Integer price;

		public VehicleForm(Long brandId, String model, Integer year, Integer price) {
			this.brandId = brandId;
			this.model = model;
			this.year = year;
			this.price = price;
		}

		public VehicleForm(Vehicle vehicle) {
			this.brandId = vehicle.getBrand().getId();
			this.model = vehicle.getModel();
			this.year = vehicle.getYear();
			this.price = vehicle.getPrice();
		}
		
		public Vehicle convert(Brand brand) {
		
			return new Vehicle(price, year, model, brand);
		}
		
		public Vehicle update(Vehicle vehicle, Brand brand) {
			
				vehicle.setBrand(brand);
				vehicle.setYear(year);
				vehicle.setModel(model);
				vehicle.setPrice(price);
				
				return vehicle;
		}
}
