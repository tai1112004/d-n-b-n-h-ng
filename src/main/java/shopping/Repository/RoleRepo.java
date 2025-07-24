package shopping.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shopping.Repository.Entity.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {
	Role findByName(String name) ; 
	 
}
