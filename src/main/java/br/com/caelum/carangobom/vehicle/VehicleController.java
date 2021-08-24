package br.com.caelum.carangobom.vehicle;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.caelum.carangobom.marca.MarcaRepository;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

	@Autowired
	private VehicleRepository vehicleRepository;
	
	@Autowired
	private MarcaRepository marcaRepository;

	@PostMapping
	public ResponseEntity<VehicleMapper> register(@RequestBody @Valid VehicleForm form, UriComponentsBuilder uriBuilder) {
		Vehicle vehicle = form.convert(marcaRepository);
					
		vehicleRepository.save(vehicle);
		
		URI uri = uriBuilder.path("/vehicle/{id}").buildAndExpand(vehicle.getId()).toUri();
		return ResponseEntity.created(uri).body(new VehicleMapper(vehicle));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<VehicleMapper> update(@PathVariable Long id, @RequestBody @Valid VehicleForm form) {
		Vehicle vehicle = form.update(id, vehicleRepository, marcaRepository);
		
		return ResponseEntity.ok(new VehicleMapper(vehicle));
	}
}
