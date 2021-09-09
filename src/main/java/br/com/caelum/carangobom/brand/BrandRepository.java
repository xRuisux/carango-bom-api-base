package br.com.caelum.carangobom.brand;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.caelum.carangobom.report.IBrandReport;


@Repository
public interface BrandRepository extends JpaRepository<Brand, Long>{

	Page<Brand> findAllByOrderByName(Pageable page);

	@Query(value = "SELECT b.id as brandId, b.name as brandName, coalesce(SUM(v.price),0.00) as totalAmount, count(v.id) as totalVehicles FROM brand AS b left join vehicle AS v on (v.brand_id = b.id) GROUP BY b.id, b.name", nativeQuery = true)
	List<IBrandReport> vehiclesByBrand();


}
