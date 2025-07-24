package shopping.request;


public class RenewRequest {

	private String content ;
	private ProductRequest product ; 


	private UserRequest user ;  
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public ProductRequest getProduct() {
		return product;
	}
	public void setProduct(ProductRequest product) {
		this.product = product;
	}
	public UserRequest getUser() {
		return user;
	}
	public void setUser(UserRequest user) {
		this.user = user;
	} 
	
}
