package shopping.model;

import java.util.List;


public class resultShipping {

	private long id ; 

	private String receiver ;

	private String phone_Receiver ;

	private String address_receiver ;

	private Boolean isDelivery ;  

	private String note ;
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

	
}
