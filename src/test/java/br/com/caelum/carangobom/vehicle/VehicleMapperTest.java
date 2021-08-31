package br.com.caelum.carangobom.vehicle;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.MockitoAnnotations.openMocks;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.caelum.carangobom.brand.Brand;

class VehicleMapperTest {
    
    @BeforeEach
    public void configuraMock() {
        openMocks(this);
    }

    @Test
    void shouldReturnAListOfVehicleMapper() {
        Brand brand = new Brand(2L, "Toyota");
        List<Vehicle> vehicleList = List.of(
            new Vehicle(300000, 2019, "Corolla", brand),
            new Vehicle(1500000, 2021, "Cross", brand)
        );

        List<VehicleMapper> vehicleMapperList = VehicleMapper.convert(vehicleList);

        assertEquals(2, vehicleMapperList.size());
    }
}