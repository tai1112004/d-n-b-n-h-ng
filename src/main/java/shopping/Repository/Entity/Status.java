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
@Table(name = "status")
public class Status {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id ; 
	@Column(name= "name")
 	private String name  ;
	@Column(name ="color")
	private String color ;
	@OneToMany(mappedBy="status",cascade = CascadeType.ALL)
	private List<Orders> orders ; 
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
	public String getColor() {
		return color;
	}
	public void setColor(String name) {
		if(name == "pending")
		{
			this.color = "yellow";
		}
		else if(name == "shipped")
		{
			this.color = "#4422EE" ; 
			
		}
		else if (name == "delivered")
		{
			this.color="#29C081" ; 
		}
		else if (name == "canceled") 
		{
			this.color ="red" ; 
		}
	}
	public List<Orders> getOrders() {
		return orders;
	}
	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}
	
	
	
}
	
