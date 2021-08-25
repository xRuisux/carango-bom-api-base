package br.com.caelum.carangobom.marca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.caelum.carangobom.erro.InternalServerErrorException;
import javassist.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
	public BrandService(BrandRepository brandRepository) {
		this.brandRepository = brandRepository;
	}

    public List<Brand> findAll() {
        return brandRepository.findAllByOrderByName();
    }

    public Brand findById(Long id) throws NotFoundException {
        Optional<Brand> brand  = brandRepository.findById(id);
        if (!brand.isPresent()) {
            throw new NotFoundException("Marca não encontrada");
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
    public Brand change(Long id, BrandForm brandDto) throws NotFoundException {
        Optional<Brand> brandFound = brandRepository.findById(id);
        
        if(brandFound.isPresent()) {
            Brand brandConvertido = brandDto.convertToBrand();            
            Brand brand = brandFound.get();
            
            brand.setName(brandConvertido.getName());
            return brand;
        }
        throw new NotFoundException("Brand não encontrada.");
    }

    @Transactional
    public Brand delete(Long id) throws NotFoundException {
        Optional<Brand> brandFound = brandRepository.findById(id);
        if (brandFound.isPresent()) {
            Brand brand = brandFound.get();
            brandRepository.delete(brand);
            return brand;
        }
        throw new NotFoundException("Marca não pode ser excluída.");
    }
}