package shopping.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shopping.Repository.Entity.BasketItem;
import shopping.Repository.Entity.Orders;
import shopping.Repository.Entity.User;

public interface UserRepo extends JpaRepository<User, Long> {
	User findByName(String name) ; 
	@Query("SELECT bi FROM BasketItem bi join bi.basket b  join b.user u where u.id = :userId") 
		List<BasketItem> findBasketItemByuser(@Param("userId") long userId);
	@Query("select COUNT(od) > 0 from OrderDetails od join od.order o join o.user  u where u.id = :userId and od.product.id = :product_id ") 
	boolean checkProductInUser(@Param("userId") long userId , @Param("product_id") long product_id) ; 
	
	@Query("select COUNT(r) >0 from Renew r where r.product.id = :product_id and r.user.id = :userId") 
	boolean checkRenewInUser(@Param("userId") long userId , @Param("product_id") long product_id  ) ; 
	
	@Query("select o from Orders o join o.user u where u.id = :userID")
	List<Orders> getListOrdersInUser(@Param("userID") long userId) ; 
	
	@Query("select distinct u from Orders o join o.status s join o.user u where s.name like :nameStatus")
	List<User> getListNameOrder(@Param("nameStatus") String nameStatus) ; 
	
	@Query("select o from Orders o join o.status s join o.user u where s.name like :nameStatus and u.id = :userId")
	List<Orders> getListOrderstoUser(@Param("nameStatus") String nameStatus , @Param("userId") long userId) ; 
	
	@Query("select COALESCE(SUM(od.quantity), 0) from OrderDetails od join od.order o join o.status t where t.name like :nameStatus" )
	 int getTotalStatiscal(@Param("nameStatus") String nameStatus) ; 
	@Query("select COALESCE(SUM(od.quantity), 0) from OrderDetails od join od.order o join o.status t where t.name like :nameStatus and month(o.order_Date)= :month" )
	 int getTotalStatiscalMonth(@Param("nameStatus") String nameStatus , @Param("month") int month) ; 
}
