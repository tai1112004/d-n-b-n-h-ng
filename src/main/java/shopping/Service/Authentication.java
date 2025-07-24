package shopping.Service;

import shopping.model.resultUser;
import shopping.request.UserRequest;

public interface Authentication {
	public resultUser register(UserRequest user);  
	public resultUser login(UserRequest user) ; 
}
