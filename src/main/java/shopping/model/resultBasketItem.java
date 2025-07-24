package shopping.model;

public class resultBasketItem {
	private long id ; 
	private resultProduct product ; 
	long quantity ;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public resultProduct getProduct() {
		return product;
	}
	public void setProduct(resultProduct product) {
		this.product = product;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	} 
	
}
