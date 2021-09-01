package br.com.caelum.carangobom.vehicle;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

public interface VehicleRepository extends Repository<Vehicle, Long> {

    public List<Vehicle> findAllByOrderByIdDesc();
    Optional<Vehicle> findById(Long id);
    Optional<Vehicle> save(Vehicle vehicle);
    Optional<Vehicle> delete(Vehicle vehicle);

}
