package shopping.Service;

import java.util.List;

import shopping.model.resultOrders;
import shopping.model.resultUser;
import shopping.request.UserRequest;

public interface UserService {
	public resultUser getProfile(String token)  ; 
	public List<resultOrders> getStatusOrderUser(String status , String token) ;
	public resultOrders getDetailOrder(long id )  ; 
	public String updateInfor(String token ,UserRequest userRequest ) ; 
}
