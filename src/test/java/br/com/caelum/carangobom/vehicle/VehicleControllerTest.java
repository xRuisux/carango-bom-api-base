package br.com.caelum.carangobom.vehicle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.caelum.carangobom.marca.Brand;
import br.com.caelum.carangobom.marca.BrandRepository;
import br.com.caelum.carangobom.marca.BrandService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

// @SpringBootTest
// @ActiveProfiles("test")
class VehicleControllerTest {

    @Autowired
    private VehicleController vehicleController;
    
    @MockBean
    private VehicleService vehicleService;
    
    @MockBean
    private BrandService brandService;

    private UriComponentsBuilder uriBuilder;
    
    // @Autowired
    // BrandService brandService;

    // @Mock
    // VehicleService vehicleService;

    // @Mock
    // VehicleRepository vehicleRepository;

    // @Mock
    // BrandRepository brandRepository;

        
    @BeforeEach
    public void configuraMock() {
        // openMocks(this);
        
        // vehicleService = new VehicleService();
        // vehicleController = new VehicleController();
        uriBuilder = UriComponentsBuilder.fromUriString("http://localhost:8080");

    }

    @Test
    void shouldReturnCreatedAfterVehicleIsCreated() throws Exception {
        
        Brand brand = new Brand(1L, "Honda");

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
                createdVehicle.setBrand(brand);

                return createdVehicle;
            });

        ResponseEntity<VehicleMapper> response = vehicleController.register(form, uriBuilder);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("http://localhost:8080/vehicle/1", response.getHeaders().getLocation().toString());  
    }
}
