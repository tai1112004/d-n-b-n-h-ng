package shopping.request;

import java.util.List;

public class OrderDetailsRequest {
	private int quantity ;
	
	private float price ;
	private String note  ; 
	private ProductRequest product ;


	private OrdersRequest order ; 

	
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
	
	public OrdersRequest getOrder() {
		return order;
	}
	public void setOrder(OrdersRequest order) {
		this.order = order;
	}

	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
}

