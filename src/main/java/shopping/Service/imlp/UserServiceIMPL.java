package shopping.Service.imlp;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import shopping.Repository.OrderRepo;
import shopping.Repository.UserRepo;
import shopping.Repository.Entity.OrderDetails;
import shopping.Repository.Entity.Orders;
import shopping.Repository.Entity.Product;
import shopping.Repository.Entity.ProductImage;
import shopping.Repository.Entity.User;
import shopping.Service.JWTService;
import shopping.Service.UserService;
import shopping.model.resultImages;
import shopping.model.resultOrderDetails;
import shopping.model.resultOrders;
import shopping.model.resultProduct;
import shopping.model.resultShipping;
import shopping.model.resultStatus;
import shopping.model.resultUser;
import shopping.request.UserRequest;
@Service
public class UserServiceIMPL implements UserService {
	@Autowired
	private JWTService jwt  ; 
	@Autowired
	private UserRepo userRepo ; 
	@Autowired
	private ModelMapper model ; 
	@Autowired
	private OrderRepo orderRepo ; 
	private BCryptPasswordEncoder bcyr = new BCryptPasswordEncoder(12) ;
	@Override
	public resultUser getProfile(String token) {
		String username = jwt.extractUserName(token)  ; 
		User userDataBase = userRepo.findByName(username) ; 
		resultUser userDTO = model.map(userDataBase , resultUser.class) ; 
		userDTO.setRole(userDataBase.getRole().getName());
		return userDTO;
	}
	@Override
	public List<resultOrders> getStatusOrderUser(String status, String token) {
		String userName = jwt.extractUserName(token)  ; 
		User userDataBase = userRepo.findByName(userName)  ; 
		List<Orders> orderListDataBase = userRepo.getListOrderstoUser(status, userDataBase.getId()) ; 
		List<resultOrders> orderListDTO = new ArrayList<>() ; 
		for(Orders order:orderListDataBase) 
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
				Product productDataBase = orderDetail.getProduct() ; 
				resultProduct productDTO = model.map(productDataBase, resultProduct.class) ; 
				productDTO.setBrand(productDataBase.getBrand().getName());
				productDTO.setCategories(productDataBase.getCategories().getName() ) ; 
				List<ProductImage> imageListDataBase  = productDataBase.getImageList() ; 
				List<resultImages> imageList = new ArrayList<>()  ; 
				for(ProductImage image : imageListDataBase)
				{
					resultImages imageDTO = model.map(image, resultImages.class) ; 
					imageList.add(imageDTO) ; 
				}
				productDTO.setImages(imageList);
				orderitem.setProduct(productDTO);
				orderDetailDTO.add(orderitem) ; 
			}
			item.setOrderDetails(orderDetailDTO);
			orderListDTO.add(item) ; 
		}
		return orderListDTO;
	}
	@Override
	public resultOrders getDetailOrder(long id) {
		Orders orderDataBase = orderRepo.findById(id).orElse(null) ; 
		resultOrders orderDTO = model.map(orderDataBase,resultOrders.class) ; 
		resultShipping shippingDTO = model.map(orderDataBase.getShipping(), resultShipping.class) ; 
		resultStatus statusDTO = model.map(orderDataBase.getStatus(), resultStatus.class)  ;
		orderDTO.setStatus(statusDTO.getName());
		orderDTO.setShipping(shippingDTO);
		List<resultOrderDetails> orderDetailDTO = new ArrayList<>()  ; 
		for(OrderDetails orderDetail : orderDataBase.getOrderDetails())
		{
			resultOrderDetails  orderitem = model.map(orderDetail, resultOrderDetails.class) ;
			Product productDataBase = orderDetail.getProduct() ; 
			resultProduct productDTO = model.map(productDataBase, resultProduct.class) ; 
			productDTO.setBrand(productDataBase.getBrand().getName());
			productDTO.setCategories(productDataBase.getCategories().getName() ) ; 
			List<ProductImage> imageListDataBase  = productDataBase.getImageList() ; 
			List<resultImages> imageList = new ArrayList<>()  ; 
			for(ProductImage image : imageListDataBase)
			{
				resultImages imageDTO = model.map(image, resultImages.class) ; 
				imageList.add(imageDTO) ; 
			}
			productDTO.setImages(imageList);
			orderitem.setProduct(productDTO);
			orderDetailDTO.add(orderitem) ; 
		}
		orderDTO.setOrderDetails(orderDetailDTO);
		return orderDTO;
	}
	@Override
	public String updateInfor(String token, UserRequest userRequest) {
		String userName = jwt.extractUserName(token) ; 
		User userDataBase = userRepo.findByName(userName)  ; 
		userDataBase.setEmail(userRequest.getEmail());
		userDataBase.setName(userRequest.getName());
		userDataBase.setPassword(bcyr.encode(userRequest.getPassword()));
		userRepo.save(userDataBase) ; 
		return "done";
	}
	
	
}
