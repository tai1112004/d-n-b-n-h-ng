package shopping.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shopping.Repository.Entity.ProductImage;

public interface ProductImageRepo extends JpaRepository<ProductImage, Long> {

}
