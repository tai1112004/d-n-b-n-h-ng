package shopping.request;

import java.util.List;


public class UserRequest {
	
	private String name ; 
	
	private String password ; 
	
	private String email ;
	
	private BasketRequest basket ; 
	
	private List<OrdersRequest> orders ;
	
	private List<RenewRequest> renew ;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BasketRequest getBasket() {
		return basket;
	}

	public void setBasket(BasketRequest basket) {
		this.basket = basket;
	}

	public List<OrdersRequest> getOrders() {
		return orders;
	}

	public void setOrders(List<OrdersRequest> orders) {
		this.orders = orders;
	}

	public List<RenewRequest> getRenew() {
		return renew;
	}

	public void setRenew(List<RenewRequest> renew) {
		this.renew = renew;
	} 
	
}
