package shopping.model;

import java.time.LocalDate;
import java.util.List;

public class resultOrders {
		private long id ; 
		private LocalDate order_Date ;
		private float total_price ; 
		private String note ;
		private resultShipping shipping ;
		private String status ; 
		private List<resultOrderDetails> orderDetails ;
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
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
		public List<resultOrderDetails> getOrderDetails() {
			return orderDetails;
		}
		public void setOrderDetails(List<resultOrderDetails> orderDetails) {
			this.orderDetails = orderDetails;
		}
	
		public resultShipping getShipping() {
			return shipping;
		}
		public void setShipping(resultShipping shipping) {
			this.shipping = shipping;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		
		
		
		
	}
