package shopping.model;

import java.util.List;

public class resultStatus {
	
	private long id ; 
	
 	private String name  ;

	private String color ;

	private List<resultOrderDetails> orderDetail  ; 
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
	public List<resultOrderDetails> getOrderDetail() {
		return orderDetail;
	}
	public void setOrderDetail(List<resultOrderDetails> orderDetail) {
		this.orderDetail = orderDetail;
	}
}
