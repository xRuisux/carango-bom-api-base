package br.com.caelum.carangobom.vehicle;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javassist.NotFoundException;


@RestController
@RequestMapping("/vehicle")
public class VehicleController {
	
	@Autowired
	VehicleService vehicleService;

	@PostMapping
	public ResponseEntity<VehicleMapper> register(@RequestBody @Valid VehicleForm form, UriComponentsBuilder uriBuilder) throws NotFoundException {
		Vehicle vehicle = vehicleService.create(form);
	
		URI uri = uriBuilder.path("/vehicle/{id}").buildAndExpand(vehicle.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new VehicleMapper(vehicle));
	}
		
	@PutMapping("/{id}")
	public ResponseEntity<VehicleMapper> update(@PathVariable Long id, @RequestBody @Valid VehicleForm form) throws NotFoundException {
		Vehicle vehicle = vehicleService.update(id, form);
		
		return ResponseEntity.ok(new VehicleMapper(vehicle));
	}
	
	@GetMapping
    public ResponseEntity<List<VehicleMapper>> list() {
        
        List<Vehicle> users = this.vehicleService.findAll();
        return ResponseEntity.ok(VehicleMapper.convert(users));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<VehicleMapper> delete(@PathVariable Long id) throws NotFoundException{
        
        Vehicle vehicle = this.vehicleService.deleteById(id);
        return ResponseEntity.ok(new VehicleMapper(vehicle));

    }
}
