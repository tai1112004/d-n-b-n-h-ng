package shopping.Repository.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "basketitem")
public class BasketItem {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY) 
	long id ; 
	@ManyToOne
	@JoinColumn(name = "product_id") 
	private Product product  ; 
	@ManyToOne
	@JoinColumn(name = "basket_id")  
	private Basket basket  ; 
	private long quantity  ;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Basket getBasket() {
		return basket;
	}
	public void setBasket(Basket basket) {
		this.basket = basket;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	} 
	
	

}
