package br.com.caelum.carangobom.brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.server.ResponseStatusException;

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
    @Cacheable(value = "brands")
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
    @CacheEvict(cacheNames = "brands", allEntries = true)
    public ResponseEntity<BrandMapper> post(@Valid @RequestBody BrandForm brandDto, UriComponentsBuilder uriBuilder) throws ResponseStatusException {
        Brand brand = brandDto.convertToBrand();
        brand = brandService.save(brand);
        URI location = uriBuilder.path("/brand/{id}").buildAndExpand(brand.getId()).toUri();
        return ResponseEntity.created(location).body(new BrandMapper(brand));       
    }

    @PutMapping("/{id}")
    @CacheEvict(cacheNames = "brands", allEntries = true)
    public ResponseEntity<BrandMapper> put(@PathVariable Long id, @Valid @RequestBody BrandForm brandDto) throws ResponseStatusException {
        Brand brand = brandService.change(id, brandDto);
        return ResponseEntity.ok(new BrandMapper(brand));
    }

    @DeleteMapping("/{id}")
    @CacheEvict(cacheNames = "brands", allEntries = true)
    public ResponseEntity<BrandMapper> delete(@PathVariable Long id) throws ResponseStatusException {
        Brand brand = brandService.delete(id);
        return ResponseEntity.ok(new BrandMapper(brand));       
    }
}
