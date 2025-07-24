package shopping.model;

import java.util.List;

public class resultOrderDetails {
	private long id ;
	private int quantity ;
	
	private float price ;
	private String note  ; 
	private resultProduct product ;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public resultProduct getProduct() {
		return product;
	}
	public void setProduct(resultProduct product) {
		this.product = product;
	}
	
}
