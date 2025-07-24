package shopping.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shopping.Repository.Entity.Brand;
@Repository
public interface BrandRepo extends JpaRepository<Brand, Long> {
	Brand findByName(String name) ; 
}
