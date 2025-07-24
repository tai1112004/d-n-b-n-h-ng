package shopping.Repository.Entity;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name ="basket")
public class Basket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id ;
	@OneToMany(mappedBy="basket",cascade = CascadeType.ALL )
	private List<BasketItem> basketItem  ; 
 	
	@OneToMany(mappedBy="basket" , cascade = CascadeType.ALL) 
	private List<User> user ;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public List<BasketItem> getBasketItem() {
		return basketItem;
	}

	public void setBasketItem(List<BasketItem> basketItem) {
		this.basketItem = basketItem;
	}

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	} 
	
	
}
