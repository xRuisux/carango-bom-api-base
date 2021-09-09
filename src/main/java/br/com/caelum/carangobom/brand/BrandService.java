package br.com.caelum.carangobom.brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import br.com.caelum.carangobom.report.IBrandReport;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    private static final String BRAND_NOT_FOUND_MESSAGE = "Marca não encontrada";

    @Autowired
	public BrandService(BrandRepository brandRepository) {
		this.brandRepository = brandRepository;
	}

    public Page<Brand> findAll(int limit, int offset) {
        Pageable page = PageRequest.of(offset, limit);
        return brandRepository.findAllByOrderByName(page);
    }

    public List<IBrandReport> getMyReport() {
        return brandRepository.vehiclesByBrand();
    }

    public Brand findById(Long id) throws ResponseStatusException {
        Optional<Brand> brand  = brandRepository.findById(id);
        if (!brand.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, BRAND_NOT_FOUND_MESSAGE);
        }
        return brand.get();
    }

    @Transactional
    public Brand save(Brand brand) throws ResponseStatusException{
        try {
            return brandRepository.save(brand);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Marca não pode ser salva.");
        }
    }

    @Transactional
    public Brand change(Long id, BrandForm brandDto) throws ResponseStatusException {
        Brand brand = this.findById(id);
    
        Brand brandConvertido = brandDto.convertToBrand();           
        
        brand.setName(brandConvertido.getName());
        return brand;

    }

    @Transactional
    public Brand delete(Long id) throws ResponseStatusException {
        Brand brandFound = this.findById(id);
        brandRepository.delete(brandFound);
        return brandFound;
    }
}