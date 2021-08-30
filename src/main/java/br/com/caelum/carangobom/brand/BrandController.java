package br.com.caelum.carangobom.brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.server.ResponseStatusException;

import br.com.caelum.carangobom.exceptions.InternalServerErrorException;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;
	
    @Autowired
	public BrandController(BrandService brandService) {
		this.brandService = brandService;
	}

    @GetMapping
    public List<Brand> get() {
    	return brandService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandMapper> getById(@PathVariable Long id) throws ResponseStatusException {
        Brand brand = brandService.findById(id);
        BrandMapper brandMapper = new BrandMapper(brand);
        return ResponseEntity.ok(brandMapper);
    }

    @PostMapping
    public ResponseEntity<BrandMapper> post(@Valid @RequestBody BrandForm brandDto, UriComponentsBuilder uriBuilder) throws InternalServerErrorException {
        Brand brand = brandDto.convertToBrand();
        brand = brandService.save(brand);
        URI location = uriBuilder.path("/brand/{id}").buildAndExpand(brand.getId()).toUri();
        return ResponseEntity.created(location).body(new BrandMapper(brand));       
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrandMapper> put(@PathVariable Long id, @Valid @RequestBody BrandForm brandDto) throws ResponseStatusException {
        Brand brand = brandService.change(id, brandDto);
        return ResponseEntity.ok(new BrandMapper(brand));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BrandMapper> delete(@PathVariable Long id) throws ResponseStatusException {
        Brand brand = brandService.delete(id);
        return ResponseEntity.ok(new BrandMapper(brand));       
    }
}
