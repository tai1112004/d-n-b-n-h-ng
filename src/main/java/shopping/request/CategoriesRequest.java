package shopping.request;

import java.util.List;

import shopping.model.resultProduct;

public class CategoriesRequest {
	long id ; 
	

	private String name ; 

	private String code ;

	private List<ProductRequest> product ;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public List<ProductRequest> getProduct() {
		return product;
	}
	public void setProduct(List<ProductRequest> product) {
		this.product = product;
	}
	
}
