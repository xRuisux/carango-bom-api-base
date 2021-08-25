package br.com.caelum.carangobom.vehicle;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.caelum.carangobom.marca.Brand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
class VehicleForm {
	
		@NotNull
		private Long brandId;
		
		@NotNull @NotEmpty
		private String model;
		
		@NotNull @Min(1979)
		private Integer year;
		
		@NotNull @Min(1000)
		private Integer price;

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
