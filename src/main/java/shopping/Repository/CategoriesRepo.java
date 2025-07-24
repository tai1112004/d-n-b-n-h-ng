package shopping.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shopping.Repository.Entity.Categories;

public interface CategoriesRepo extends JpaRepository<Categories, Long> {
	Categories findByName(String name) ; 
}
