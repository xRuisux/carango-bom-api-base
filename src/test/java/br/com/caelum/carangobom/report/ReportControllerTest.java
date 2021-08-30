package br.com.caelum.carangobom.report;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest
@ActiveProfiles("test")
public class ReportControllerTest {
    @Autowired
    private ReportController reportController;

    @Test
    void shouldReturnAllBrandsWithTotalVehiclesAndAmountOfEach() throws Exception {

        ResponseEntity<List<IBrandReport>> response = reportController.getBrandReport(); 
        assertEquals(1, response.getBody().size());
        assertEquals(0, response.getBody().get(0).getTotalVehicles());
        assertEquals(0, response.getBody().get(0).getTotalAmount());

    }
}
