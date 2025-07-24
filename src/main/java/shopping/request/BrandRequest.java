package shopping.request;

import java.util.List;


public class BrandRequest {
	String name ; 
	String code ;
	private List<ProductRequest> product ; 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	} 
	
}
