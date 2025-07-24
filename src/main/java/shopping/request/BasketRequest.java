package shopping.request;

import java.util.List;


public class BasketRequest {
	private List<ProductRequest> product ; 
	private List<UserRequest> user ;

	public List<ProductRequest> getProduct() {
		return product;
	}

	public void setProduct(List<ProductRequest> product) {
		this.product = product;
	}

	public List<UserRequest> getUser() {
		return user;
	}

	public void setUser(List<UserRequest> user) {
		this.user = user;
	} 
	
}
