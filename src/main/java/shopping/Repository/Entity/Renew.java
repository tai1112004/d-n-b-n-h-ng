package shopping.Repository.Entity;


import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name= "renew")
public class Renew {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id ; 
	@Column(name = "content")
	private String content ;
	@Column(name="rating")
	private int rating ; 
	@Column(name = "date")
	private LocalDate date ; 
	@ManyToOne
	@JoinColumn(name = "product_id") 
	private Product product ; 
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id") 
	private User user ;  
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	} 
	
	
}
