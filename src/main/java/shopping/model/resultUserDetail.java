package shopping.model;

import java.util.List;

public class resultUserDetail {
	private Long id ; 
	private String name , email , role ;
	private List<resultOrders>   order ;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public List<resultOrders> getOrder() {
		return order;
	}
	public void setOrder(List<resultOrders> order) {
		this.order = order;
	} 
	
	
}
