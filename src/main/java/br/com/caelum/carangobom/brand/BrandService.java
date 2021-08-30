package br.com.caelum.carangobom.brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import br.com.caelum.carangobom.exceptions.InternalServerErrorException;

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

    public List<Brand> findAll() {
        return brandRepository.findAllByOrderByName();
    }

    public Brand findById(Long id) throws ResponseStatusException {
        Optional<Brand> brand  = brandRepository.findById(id);
        if (!brand.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, BRAND_NOT_FOUND_MESSAGE);
        }
        return brand.get();
    }

    @Transactional
    public Brand save(Brand brand) throws InternalServerErrorException{
        Brand savedBrand = new Brand();
        try {
            savedBrand = brandRepository.save(brand);
            return savedBrand;
        } catch (Exception e) {
            throw new InternalServerErrorException("Marca não pode ser salva.");
        }
    }

    @Transactional
    public Brand change(Long id, BrandForm brandDto) throws ResponseStatusException {
        Optional<Brand> brandFound = brandRepository.findById(id);
        
        if(brandFound.isPresent()) {
            Brand brandConvertido = brandDto.convertToBrand();            
            Brand brand = brandFound.get();
            
            brand.setName(brandConvertido.getName());
            return brand;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, BRAND_NOT_FOUND_MESSAGE);
    }

    @Transactional
    public Brand delete(Long id) throws ResponseStatusException {
        Optional<Brand> brandFound = brandRepository.findById(id);
        if (brandFound.isPresent()) {
            Brand brand = brandFound.get();
            brandRepository.delete(brand);
            return brand;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, BRAND_NOT_FOUND_MESSAGE);
    }
}