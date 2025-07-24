package shopping.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shopping.Repository.Entity.Status;

public interface StatusRepo extends JpaRepository<Status,Long>{
	Status findByName(String name)  ; 
}
