package shopping.Repository.Entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="orderdetails")
public class OrderDetails {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id ; 
	@Column(name = "quantity")
	private int quantity ;
	@Column(name = "price") 
	private float price ;
	@ManyToOne
	@JoinColumn(name="product_id")  
	private Product product ;
	@ManyToOne
	@JoinColumn(name="order_id")
	private Orders order ; 
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Orders getOrder() {
		return order;
	}
	public void setOrder(Orders order) {
		this.order = order;
	}

	
	
	
	
	
	
}
