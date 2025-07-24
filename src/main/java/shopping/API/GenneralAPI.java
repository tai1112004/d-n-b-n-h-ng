package shopping.API;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import shopping.Service.ProductService;
import shopping.model.resultBrand;
import shopping.model.resultCategories;
import shopping.model.resultProduct;
import shopping.request.BrandRequest;
import shopping.request.CategoriesRequest;
import shopping.request.ProductRequest;

@RestController
public class GenneralAPI {
	@Autowired
	private  ProductService service  ; 
	
	
	@GetMapping("/api/getProducts") 
	public  List<resultProduct> getproduct()
	{
		
		return service.getAllProduct() ;  
	}
	@GetMapping("/api/getProducts/{id}")
	public resultProduct getDetailProduct(@PathVariable long id)
	{
		return service.getDetailProduct(id) ; 
	}
	
	@GetMapping("/api/getBrands")
	public List<resultBrand> getBrand()
	{
		return service.getAllBrand() ; 
	}
	@GetMapping("/api/getCategories")
	public List<resultCategories> getCategories() 
	{
		return service.getAllCategories() ; 
	}
	@GetMapping("/api/getProducts/Categories") 
	public List<resultProduct> getProductByCategories(@RequestBody CategoriesRequest data  )
	{
		return service.getProductsByCategories(data) ; 
	}
	@GetMapping("/api/getProducts/Brand")
	public List<resultProduct> getProductByBrand(@RequestBody BrandRequest data) 
	{
		return service.getProductsByBrand(data)  ; 
	}
	
	
}
