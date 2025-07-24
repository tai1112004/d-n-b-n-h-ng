package shopping.Repository.Entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id ;
	@Column(name = "name")
	private String name ; 
	@Column(name = "password")
	private String password ; 
	@Column(name = "email")
	private String email ;
	@ManyToOne
	@JoinColumn(name ="basket_id") 
	private Basket basket ; 
	@OneToMany(mappedBy="user" , cascade = CascadeType.ALL )
	private List<Orders> orders ;
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL) 
	private List<Renew> renew ; 
	@ManyToOne
	@JoinColumn(name ="role_id")
	private Role role ; 
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
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
	public Basket getBasket() {
		return basket;
	}
	public void setBasket(Basket basket) {
		this.basket = basket;
	}
	public List<Orders> getOrders() {
		return orders;
	}
	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}
	public List<Renew> getRenew() {
		return renew;
	}
	public void setRenew(List<Renew> renew) {
		this.renew = renew;
	} 
	
	 
	
}
