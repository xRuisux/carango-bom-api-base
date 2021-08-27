package br.com.caelum.carangobom.vehicle;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

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

  @Transactional
  public Vehicle create(VehicleForm form) throws NotFoundException {
    
    Brand brand = getBrandFromId(form.getBrandId());
    
    Vehicle vehicle = form.convert(brand);
    
    vehicleRepository.save(vehicle);
    return vehicle;
  }

  @Transactional
  public Vehicle update(Long id, VehicleForm form) throws NotFoundException {
      
    Brand brand = getBrandFromId(form.getBrandId());
    
    Vehicle vehicle = findById(id);
    vehicle = form.update(vehicle, brand);
    vehicleRepository.save(vehicle);
    return vehicle;
  }

  private Vehicle findById(Long id) throws NotFoundException {
   
    Optional<Vehicle> vehicle = vehicleRepository.findById(id);
    if(!vehicle.isPresent()) {
      throw new NotFoundException("Veículo não encontrado"); 
    }
    return vehicle.get();
  }

  private Brand getBrandFromId(Long id) throws NotFoundException {
    return brandService.findById(id);
  }

  public List<Vehicle> findAll() {
    return vehicleRepository.findAllByOrderByIdDesc();
  }
  
  @Transactional
  public Vehicle deleteById(Long id) throws NotFoundException{
    Vehicle vehicle = this.findById(id);
    this.vehicleRepository.delete(vehicle);
    return vehicle;
  }

}
