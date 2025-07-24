package shopping.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shopping.Repository.Entity.BasketItem;

public interface BasketItemRepo extends JpaRepository<BasketItem,Long> {

}
