package br.com.caelum.carangobom.vehicle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.caelum.carangobom.erro.InternalServerErrorException;
import br.com.caelum.carangobom.marca.Brand;
import br.com.caelum.carangobom.marca.BrandRepository;
import br.com.caelum.carangobom.marca.BrandService;
import javassist.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class VehicleControllerTest {

    private VehicleController vehicleController;
    private UriComponentsBuilder uriBuilder;

    @Mock
    BrandService brandService;

    @Mock
    VehicleService vehicleService;

    @Mock
    VehicleRepository vehicleRepository;

    @Mock
    BrandRepository brandRepository;

        
    @BeforeEach
    public void configuraMock() {
        openMocks(this);
        
        vehicleService = new VehicleService();
        vehicleController = new VehicleController();
        uriBuilder = UriComponentsBuilder.fromUriString("http://localhost:8080");

    }

    @Test
    void shouldReturnCreatedAfterVehicleIsCreated() throws InternalServerErrorException, NotFoundException {
        Brand brand = new Brand("Honda");
        
        when(brandService.save(brand))
            .then(invocation -> {
                    Brand savedBrand = invocation.getArgument(0, Brand.class);
                    savedBrand.setId(1L);
    
                    return savedBrand;
                });
        
        Vehicle vehicle = new Vehicle(800000, 2020, "CR-V", brand);
        VehicleForm form = new VehicleForm(vehicle);

        when(vehicleService.create(form))
            .then(invocation -> {
                Vehicle createdVehicle = invocation.getArgument(0, Vehicle.class);
                createdVehicle.setId(2L);

                return createdVehicle;
            });

            ResponseEntity<VehicleMapper> response = vehicleController.register(form, uriBuilder);
            assertEquals(HttpStatus.CREATED, response.getStatusCode());
            assertEquals("http://localhost:8080/vehicle/1", response.getHeaders().getLocation().toString());
    }
}
