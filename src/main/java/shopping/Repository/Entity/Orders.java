package shopping.Repository.Entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name= "orders")
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id ; 
	@Column(name ="order_date")
	private LocalDate order_Date ;
	@Column(name ="total_price")
	private float total_price ; 
	@Column(name = "note")
	private String note ;
	@OneToMany(mappedBy = "order" , cascade = CascadeType.ALL) 
	private List<OrderDetails> orderDetails ;
	@ManyToOne
	@JoinColumn(name ="user_id")
	private User user ; 
	@ManyToOne 
	@JoinColumn(name ="shipping_id")
	private Shipping shipping ;
	@ManyToOne
	@JoinColumn(name = "status_id")
	private Status status ; 
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public LocalDate getOrder_Date() {
		return order_Date;
	}
	public void setOrder_Date(LocalDate order_Date) {
		this.order_Date = order_Date;
	}
	public float getTotal_price() {
		return total_price;
	}
	public void setTotal_price(float total_price) {
		this.total_price = total_price;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public List<OrderDetails> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(List<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Shipping getShipping() {
		return shipping;
	}
	public void setShipping(Shipping shipping) {
		this.shipping = shipping;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	
	
}
