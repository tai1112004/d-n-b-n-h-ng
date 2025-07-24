package shopping.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shopping.Repository.Entity.OrderDetails;

public interface OrderDetailsRepo extends JpaRepository<OrderDetails, Long>{

}
