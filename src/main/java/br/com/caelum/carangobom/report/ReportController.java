package br.com.caelum.carangobom.report;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.caelum.carangobom.brand.BrandService;


@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private BrandService brandService;
    
    @GetMapping("/brand")
    public ResponseEntity<List<IBrandReport>> getBrandReport() {
        
        List<IBrandReport> brandReport = this.brandService.getMyReport();
        return ResponseEntity.ok(brandReport);

    }
}
