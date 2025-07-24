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
@Table(name = "shipping")
public class Shipping {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id ; 
	@Column(name = "reveiver")
	private String receiver ;
	@Column(name ="phone_Receiver")
	private String phone_Receiver ;
	@Column(name ="address_receiver")
	private String address_receiver ;
	@Column(name = "isDelivery") 
	private Boolean isDelivery ;  
	@Column(name ="note") 
	private String note ;
	@OneToMany(mappedBy="shipping" , cascade = CascadeType.ALL)
	private List<Orders> orders ; 
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getPhone_Receiver() {
		return phone_Receiver;
	}
	public void setPhone_Receiver(String phone_Receiver) {
		this.phone_Receiver = phone_Receiver;
	}
	public String getAddress_receiver() {
		return address_receiver;
	}
	public void setAddress_receiver(String address_receiver) {
		this.address_receiver = address_receiver;
	}
	public Boolean getIsDelivery() {
		return isDelivery;
	}
	public void setIsDelivery(Boolean isDelivery) {
		this.isDelivery = isDelivery;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public List<Orders> getOders() {
		return orders;
	}
	public void setOders(List<Orders> orders) {
		this.orders = orders;
	}
	
	
	
	
	
	
}
