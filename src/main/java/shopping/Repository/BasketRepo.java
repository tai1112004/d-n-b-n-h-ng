package shopping.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shopping.Repository.Entity.Basket;

public interface BasketRepo extends JpaRepository<Basket,Long> {
}
