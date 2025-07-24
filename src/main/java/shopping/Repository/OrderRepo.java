package shopping.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shopping.Repository.Entity.OrderDetails;
import shopping.Repository.Entity.Orders;

public interface OrderRepo extends JpaRepository<Orders, Long> {
	@Query("select od from OrderDetails od join od.order o where o.id = :ordersID")
	List<OrderDetails> getOrderDetailsInOrders(@Param("ordersID") long id) ;
}
