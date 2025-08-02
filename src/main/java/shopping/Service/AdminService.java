package shopping.Service;

import java.util.List;

import shopping.model.ResultListNameOrder;
import shopping.model.resultOrders;
import shopping.model.resultUser;
import shopping.model.resultUserDetail;
import shopping.request.ProductRequest;
import shopping.request.RoleRequest;

public interface AdminService {
	public List<resultUser> getUser() ; 
	public resultUserDetail getUserDetail(long id ) ; 
	public String changeRoleUser(RoleRequest roleRequest, long id)  ; 
	public String deleteUser(long id)  ; 
	public List<resultOrders> getListOrder(String nameStatus) ; 
	public List<resultOrders> getListOrder(String nameStatus, long id)  ; 
	public String changeStatusOrder(String nameStatus , long idOrder)  ; 
	public int totalStatiscal(String nameStatus)  ; 
	public int totalStatiscalMonth(String nameStatus , int month) ;
	public String UpdateProduct(ProductRequest product) ;
	public String delProduct(long id)  ; 
  }
