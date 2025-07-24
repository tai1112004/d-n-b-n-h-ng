package shopping.Service.imlp;

import java.util.ArrayList;
import java.util.List;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import shopping.Repository.OrderRepo;
import shopping.Repository.RoleRepo;
import shopping.Repository.StatusRepo;
import shopping.Repository.UserRepo;
import shopping.Repository.Entity.OrderDetails;
import shopping.Repository.Entity.Orders;
import shopping.Repository.Entity.Role;
import shopping.Repository.Entity.Status;
import shopping.Repository.Entity.User;
import shopping.Service.AdminService;
import shopping.model.ResultListNameOrder;
import shopping.model.resultOrderDetails;
import shopping.model.resultOrders;
import shopping.model.resultProduct;
import shopping.model.resultShipping;
import shopping.model.resultStatus;
import shopping.model.resultUser;
import shopping.model.resultUserDetail;
import shopping.request.RoleRequest;
@Service
public class AdminServiceIMPL implements AdminService {
	@Autowired
	UserRepo userRepo ; 
	@Autowired
	RoleRepo roleRepo ; 
	@Autowired
	private OrderRepo orderRepo ; 
	@Autowired
	private StatusRepo statusRepo ; 
	@Autowired
	ModelMapper model ; 
	@Override
	public List<resultUser> getUser() {
		List<User> userDataBase = userRepo.findAll() ;
		List<resultUser> list_userDTO = new ArrayList<>() ;
		for(User user : userDataBase)
		{
			resultUser userDTO = model.map(user, resultUser.class) ; 
			userDTO.setRole(user.getRole().getName()) ; 
			list_userDTO.add(userDTO) ; 
		}
		return list_userDTO;
	}
	@Override
	public resultUserDetail getUserDetail(long id) {
		User userDataBase = userRepo.findById(id).orElse(null) ;
		List<Orders> ordersDataBaseList = userRepo.getListOrdersInUser(id) ; 
		List<resultOrders> ordersListDTO = new ArrayList<>()  ; 
		resultUserDetail userDTO = model.map(userDataBase, resultUserDetail.class)  ; 
		for(Orders ordersDataBase: ordersDataBaseList)
		{
			resultOrders ordersDTO = model.map(ordersDataBase, resultOrders.class)  ; 
			resultShipping shippingDTO = model.map(ordersDataBase.getShipping(),resultShipping.class)  ;
			ordersDTO.setShipping(shippingDTO);
			ordersDTO.setStatus(ordersDataBase.getStatus().getName());
			List<OrderDetails> orderDetailsDataBaseList = orderRepo.getOrderDetailsInOrders(ordersDataBase.getId()) ; 
			List<resultOrderDetails> orderDetailsListDTO = new ArrayList<>() ; 
			for(OrderDetails orderDetailsDataBase : orderDetailsDataBaseList)
			{
				resultOrderDetails orderDetailsDTO = model.map(orderDetailsDataBase, resultOrderDetails.class) ; 
				resultProduct productDTO  = model.map(orderDetailsDataBase.getProduct(), resultProduct.class) ; 
				orderDetailsDTO.setProduct(productDTO);
				orderDetailsListDTO.add(orderDetailsDTO) ; 
			}
			ordersDTO.setOrderDetails(orderDetailsListDTO); 
			ordersListDTO.add(ordersDTO) ; 
 		}
		userDTO.setOrder(ordersListDTO);
		return userDTO ; 
		
	}
	@Override
	public String changeRoleUser(RoleRequest roleRequest, long id) {
		User userDataBase = userRepo.findById(id).orElse(null )  ; 
		Role roleDataBase = roleRepo.findByName(roleRequest.getName())  ; 
		if(roleDataBase==null)
		{
			roleDataBase = new Role()  ;
			roleDataBase = model.map(roleRequest, Role.class)  ; 
		}
		roleRepo.save(roleDataBase) ; 
		userDataBase.setRole(roleDataBase); 
		userRepo.save(userDataBase) ; 
		return "da thay doi" ;
	}
	@Override
	public String deleteUser(long id) {
		userRepo.deleteById(id); 
		return "da xoa" ;
	}
	@Override
	public List<ResultListNameOrder> getListOrder(String nameStatus) {
		List<User> userDataBase = userRepo.getListNameOrder(nameStatus) ; 
		List<ResultListNameOrder> listNameOrder = new ArrayList<>() ; 
		for(User user : userDataBase )
		{
			ResultListNameOrder nameOrder = new ResultListNameOrder() ; 
			nameOrder.setNameOrder(user.getName());
			nameOrder.setId(user.getId());
			listNameOrder.add(nameOrder)  ; 
		}
		return listNameOrder;
	}
	@Override
	public List<resultOrders> getListOrder(String nameStatus, long id) {
		List<Orders> ordersDataBase = userRepo.getListOrderstoUser(nameStatus, id) ;
		List<resultOrders> orderListDTO = new ArrayList<>() ; 
		for(Orders order:ordersDataBase) 
		{
			resultOrders item = model.map(order,resultOrders.class) ; 
			resultShipping shippingDTO = model.map(order.getShipping(), resultShipping.class) ; 
			resultStatus statusDTO = model.map(order.getStatus(), resultStatus.class)  ;
			item.setStatus(statusDTO.getName());
			item.setShipping(shippingDTO);
			List<resultOrderDetails> orderDetailDTO = new ArrayList<>()  ; 
			for(OrderDetails orderDetail : order.getOrderDetails())
			{
				resultOrderDetails  orderitem = model.map(orderDetail, resultOrderDetails.class) ;
				orderDetailDTO.add(orderitem) ; 
			}
			item.setOrderDetails(orderDetailDTO);
			orderListDTO.add(item) ; 
		}
		return orderListDTO;
	}
	@Override
	public String changeStatusOrder(String nameStatus, long idOrder) {
		Orders orderDataBase = orderRepo.findById(idOrder).orElse(null) ; 
		Status statusDataBase = statusRepo.findByName(nameStatus) ; 
		if(statusDataBase==null)
		{
			statusDataBase = new Status() ; 
			statusDataBase.setName(nameStatus); 
			statusDataBase.setColor(nameStatus);
			statusRepo.save(statusDataBase) ; 
		}
		orderDataBase.setStatus(statusDataBase);
		orderRepo.save(orderDataBase)  ; 
		return "done";
	}
	@Override
	public int totalStatiscal(String nameStatus) {
		Integer total = userRepo.getTotalStatiscal(nameStatus) ; 
		return total;
	}
	@Override
	public int totalStatiscalMonth(String nameStatus, int month) {
		int total = userRepo.getTotalStatiscalMonth(nameStatus, month) ; 
		return total;
	}

}
