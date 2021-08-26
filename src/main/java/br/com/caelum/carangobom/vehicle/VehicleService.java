package br.com.caelum.carangobom.vehicle;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.caelum.carangobom.marca.Brand;
import br.com.caelum.carangobom.marca.BrandService;
import javassist.NotFoundException;

@Service
class VehicleService {
  
	@Autowired
	private VehicleRepository vehicleRepository;

	@Autowired
	private BrandService brandService;

  public Vehicle create(VehicleForm form) throws NotFoundException {
    
      Brand brand = getBrandFromId(form.getBrandId());
      
      Vehicle vehicle = form.convert(brand);
      
      vehicleRepository.save(vehicle);
      return vehicle;
  }

  public Vehicle update(Long id, VehicleForm form) throws NotFoundException {
      
      Brand brand = getBrandFromId(form.getBrandId());
      
      Optional<Vehicle> vehicle = vehicleRepository.findById(id);
      if(!vehicle.isPresent()) {
        throw new NotFoundException("Veículo não encontrado"); 
      }

      return form.update(vehicle.get(), brand);
  }

    private Brand getBrandFromId(Long id) throws NotFoundException {
        return brandService.findById(id);
    }
}
