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
				this.brandName = vehicle.getBrand().getName();
				this.price = vehicle.getPrice();
				this.model = vehicle.getModel();
		}
		
		public static List<VehicleMapper> convert(List<Vehicle> vehicles) {
				return vehicles.stream().map(VehicleMapper::new).collect(Collectors.toList());
		}
}
