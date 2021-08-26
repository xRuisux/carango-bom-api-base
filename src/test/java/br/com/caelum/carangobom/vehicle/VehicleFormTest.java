package br.com.caelum.carangobom.vehicle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.caelum.carangobom.marca.Brand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.MockitoAnnotations.openMocks;

class VehicleFormTest {
    
    @BeforeEach
    public void configuraMock() {
        openMocks(this);
    }

    @Test
    void shouldConvertToVehicle() {
        Brand brand = new Brand(1L, "Honda");
        VehicleForm vehicleForm = new VehicleForm(1L, "Civic", 1980, 200000);
        Vehicle vehicle = vehicleForm.convert(brand);

        assertEquals(vehicle.getBrand().getName(), brand.getName());

    }

    @Test
    void shouldUpdateVehicleInfo() {
        Brand brand = new Brand(1L, "Honda");
        Vehicle vehicle = new Vehicle(200000, 1980, "Civic", brand);
        VehicleForm vehicleForm = new VehicleForm(1L, "Accord", 2021, 900000);

        vehicle = vehicleForm.update(vehicle, brand);

        assertEquals("Accord", vehicle.getModel());
        assertEquals(900000, vehicle.getPrice());
        assertEquals(2021, vehicle.getYear());
        assertEquals("Honda", vehicle.getBrand().getName());
    }
}
