package shopping.Repository.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "categories") 
public class Categories {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id ;
	
	@Column(name ="name")
	private String name ; 
	@Column(name ="code")
	private String code ;
	@OneToMany(mappedBy="categories",cascade = CascadeType.ALL)
	private List<Product> product ;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public List<Product> getProduct() {
		return product;
	}
	public void setProduct(List<Product> product) {
		this.product = product;
	} 
	
	

}
