package shopping.request;

import java.util.List;



public class ShippingRequest {

	private String receiver ;

	private String phone_Receiver ;

	private String address_receiver ;

	private Boolean isDelivery ;  

	private String note ;

	private List<OrderDetailsRequest> oderDetails ; 
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
	public List<OrderDetailsRequest> getOderDetails() {
		return oderDetails;
	}
	public void setOderDetails(List<OrderDetailsRequest> oderDetails) {
		this.oderDetails = oderDetails;
	} 
}
