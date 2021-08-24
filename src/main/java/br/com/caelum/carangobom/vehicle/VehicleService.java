package br.com.caelum.carangobom.vehicle;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.caelum.carangobom.marca.Marca;
import br.com.caelum.carangobom.marca.MarcaRepository;

@Service
public class VehicleService {
  
	@Autowired
	private VehicleRepository vehicleRepository;

	@Autowired
	private MarcaRepository marcaRepository;

  public Vehicle createVehicle(VehicleForm form) {
      // SUBSTITUIR PELO MARCA SERVICE
      Marca brand = marcaRepository.getOne(form.getBrandId());
      
      Vehicle vehicle = form.convert(brand);
            
      vehicleRepository.save(vehicle);
      return vehicle;
  }

  public Vehicle update(Long id, VehicleForm form) throws NoSuchElementException {
      // SUBSTITUIR PELO MARCA SERVICE
      Marca brand = marcaRepository.getOne(form.getBrandId());
      
      Optional<Vehicle> vehicle = vehicleRepository.findById(id);
      if(!vehicle.isPresent()) {
        throw new NoSuchElementException("Veículo não encontrado"); 
      }

      Vehicle updatedVehicle = form.update(vehicle.get(), brand);
      return updatedVehicle;
  }
}
