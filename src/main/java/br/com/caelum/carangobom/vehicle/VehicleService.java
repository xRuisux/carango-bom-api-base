package br.com.caelum.carangobom.vehicle;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import br.com.caelum.carangobom.brand.Brand;
import br.com.caelum.carangobom.brand.BrandService;

@Service
class VehicleService {
  
	@Autowired
	private VehicleRepository vehicleRepository;

	@Autowired
	private BrandService brandService;

  public Vehicle create(VehicleForm form) throws ResponseStatusException {
    
    Brand brand = getBrandFromId(form.getBrandId());
    
    Vehicle vehicle = form.convert(brand);
    
    vehicleRepository.save(vehicle);
    return vehicle;
  }

  @Transactional
  public Vehicle update(Long id, VehicleForm form) throws ResponseStatusException {
      
    Brand brand = getBrandFromId(form.getBrandId());
    
    Vehicle vehicle = findById(id);
    vehicle = form.update(vehicle, brand);
    vehicleRepository.save(vehicle);
    return vehicle;
  }

  private Vehicle findById(Long id) throws ResponseStatusException {
   
    Optional<Vehicle> vehicle = vehicleRepository.findById(id);
    if(!vehicle.isPresent()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Veículo não encontrado"); 
    }
    return vehicle.get();
  }

  public List<Vehicle> findAll() {
    return vehicleRepository.findAllByOrderByIdDesc();
  }
  
  @Transactional
  public Vehicle deleteById(Long id) throws ResponseStatusException{
    Vehicle vehicle = this.findById(id);
    this.vehicleRepository.delete(vehicle);
    return vehicle;
  }

  private Brand getBrandFromId(Long id) throws ResponseStatusException {
      return brandService.findById(id);
  }
}
