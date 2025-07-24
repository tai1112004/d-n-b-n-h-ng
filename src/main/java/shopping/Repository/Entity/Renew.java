package shopping.Repository.Entity;


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
	@ManyToOne
	@JoinColumn(name = "product_id") 
	private Product product ; 
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id") 
	private User user ;  
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
