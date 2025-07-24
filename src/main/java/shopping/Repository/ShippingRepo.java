package shopping.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shopping.Repository.Entity.Shipping;

public interface ShippingRepo extends JpaRepository<Shipping, Long> {

}
