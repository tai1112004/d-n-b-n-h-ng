package shopping.request;

import java.time.LocalDate;
import java.util.List;

public class OrdersRequest {
	private LocalDate order_Date ;
	private float total_price ; 
	private String note ;
	private OrderDetailsRequest orderDetails ;
	private UserRequest user ; 
	private ShippingRequest shipping ;
	private StatusRequest status ; 
	
	public ShippingRequest getShipping() {
		return shipping;
	}
	public void setShipping(ShippingRequest shipping) {
		this.shipping = shipping;
	}
	public StatusRequest getStatus() {
		return status;
	}
	public void setStatus(StatusRequest status) {
		this.status = status;
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
	
	public OrderDetailsRequest getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(OrderDetailsRequest orderDetails) {
		this.orderDetails = orderDetails;
	}
	public UserRequest getUser() {
		return user;
	}
	public void setUser(UserRequest user) {
		this.user = user;
	}
	
	
}
