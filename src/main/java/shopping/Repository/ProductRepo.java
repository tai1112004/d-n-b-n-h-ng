package shopping.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shopping.Repository.Entity.Product;
@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
}
