package shopping.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import shopping.Repository.Entity.Product;
import shopping.Repository.Entity.ProductImage;
import shopping.Repository.Entity.Renew;
@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
	@Query("select r from Renew r join r.product p where p.id = :productId")
	public List<Renew> getListRenew (@Param("productId") long id)  ; 
	
	@Query("select i from ProductImage i join i.product p where p.id = :productId")  
	public List<ProductImage> getListImage(@Param("productId") long productId) ; 
	
	@Query("select p from Product p where p.discount > 0 ")
	public List<Product> getProductDiscount() ; 
}
