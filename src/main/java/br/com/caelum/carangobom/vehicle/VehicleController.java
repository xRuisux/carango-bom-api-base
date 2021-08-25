package br.com.caelum.carangobom.vehicle;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
			System.out.println("ENTROU VEHICLE CONTROLLER 1");
			Vehicle vehicle = vehicleService.create(form);
			System.out.println("ENTROU VEHICLE CONTROLLER 2");
				URI uri = uriBuilder.path("/vehicle/{id}").buildAndExpand(vehicle.getId()).toUri();
				System.out.println("ENTROU VEHICLE CONTROLLER 3");
				return ResponseEntity.created(uri).body(new VehicleMapper(vehicle));
		}
		
		@PutMapping("/{id}")
		@Transactional
		public ResponseEntity<VehicleMapper> update(@PathVariable Long id, @RequestBody @Valid VehicleForm form)  throws NotFoundException {
				Vehicle vehicle = vehicleService.update(id, form);
				
				return ResponseEntity.ok(new VehicleMapper(vehicle));
		}
}
