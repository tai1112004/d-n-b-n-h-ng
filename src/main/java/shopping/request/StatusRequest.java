package shopping.request;

import java.util.List;


public class StatusRequest {
	
 	private String name  ;

	private String color ;

	private List<OrderDetailsRequest> orderDetail  ; 
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
}
