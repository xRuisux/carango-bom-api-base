package br.com.caelum.carangobom.vehicle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.caelum.carangobom.marca.Brand;
import javassist.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
class VehicleControllerTest {

    @Autowired
    private VehicleController vehicleController;
    
    // @MockBean
    // private VehicleService vehicleService;
    
    // @MockBean
    // private BrandService brandService;

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
    public void prepareTest() {
        // openMocks(this);
        
        // vehicleService = new VehicleService();
        // vehicleController = new VehicleController();
        uriBuilder = UriComponentsBuilder.fromUriString("http://localhost:8080");

    }

    @Test
    void shouldReturnCreatedAfterVehicleIsCreated() throws Exception {
        
        Brand brand = new Brand(1L, "Honda");
        
        Vehicle vehicle = new Vehicle(800000, 2020, "CR-V", brand);
        VehicleForm form = new VehicleForm(vehicle);

        ResponseEntity<VehicleMapper> response = vehicleController.register(form, uriBuilder);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("http://localhost:8080/vehicle/1", response.getHeaders().getLocation().toString());  
    }

    @Test
    void shouldReturnOkAfterVehicleUpdate() throws Exception {
        Brand brand = new Brand(1L, "Honda");

        Vehicle vehicle = new Vehicle(900000, 2020, "CR-V", brand);
        VehicleForm form = new VehicleForm(vehicle);

        ResponseEntity<VehicleMapper> response = vehicleController.update(1L, form);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(900000, response.getBody().getPrice());
    }

    @Test
    void shouldReturnNotFoundExceptionWhenUpdateVehicleThatNotExist() throws Exception {
        Brand brand = new Brand(1L, "Honda");

        Vehicle vehicle = new Vehicle(800000, 2020, "CR-V", brand);
        VehicleForm form = new VehicleForm(vehicle);

        assertThrows(NotFoundException.class, () -> {
            vehicleController.update(2L, form);
        });
    }

    @Test
    void shouldReturnAllVehicles() throws Exception {

        ResponseEntity<List<VehicleMapper>> response = vehicleController.list(); 
        assertEquals(1, response.getBody().size());
    }

    @Test
    void shouldDeleteUserWhenItExists() throws Exception {

        ResponseEntity<VehicleMapper> response = vehicleController.delete(1l); 
        assertEquals(1L, response.getBody().getId());
        assertEquals(2020, response.getBody().getYear());
        assertEquals(900000 , response.getBody().getPrice());
        assertEquals("CR-V", response.getBody().getModel());


        assertThrows(NotFoundException.class,  () -> {
            vehicleController.delete(1L);
        });
    }
}