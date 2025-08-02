package shopping.Service.imlp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.utils.ObjectUtils;

import shopping.Repository.BrandRepo;
import shopping.Repository.CategoriesRepo;
import shopping.Repository.OrderRepo;
import shopping.Repository.ProductRepo;
import shopping.Repository.RoleRepo;
import shopping.Repository.StatusRepo;
import shopping.Repository.UserRepo;
import shopping.Repository.Entity.Brand;
import shopping.Repository.Entity.Categories;
import shopping.Repository.Entity.OrderDetails;
import shopping.Repository.Entity.Orders;
import shopping.Repository.Entity.Product;
import shopping.Repository.Entity.ProductImage;
import shopping.Repository.Entity.Role;
import shopping.Repository.Entity.Status;
import shopping.Repository.Entity.User;
import shopping.Service.AdminService;
import shopping.Service.CloudinaryService;
import shopping.model.ResultListNameOrder;
import shopping.model.resultOrderDetails;
import shopping.model.resultOrders;
import shopping.model.resultProduct;
import shopping.model.resultShipping;
import shopping.model.resultStatus;
import shopping.model.resultUser;
import shopping.model.resultUserDetail;
import shopping.request.ProductRequest;
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
	@Autowired
	private CategoriesRepo categoriesRepo ; 
	@Autowired
	private BrandRepo brandRepo ; 
	@Autowired
	private ProductRepo productRepo ; 
	@Autowired
	private CloudinaryService cloudinary ; 
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
	public List<resultOrders> getListOrder(String nameStatus) {
		List<Orders> ordersListDataBase = orderRepo.getListOrder(nameStatus) ; 
		List<resultOrders> orderListDTO = new ArrayList<>() ;
		for(Orders orders : ordersListDataBase )
		{
			resultOrders orderDTO = model.map(orders, resultOrders.class) ; 
			resultUser userDTO = model.map(orders.getUser(), resultUser.class)  ; 
			orderDTO.setUser(userDTO); 
			orderDTO.setStatus(orders.getStatus().getName());
			resultShipping shipDTO = model.map(orders.getShipping(), resultShipping.class) ; 
			orderDTO.setShipping(shipDTO);
			orderListDTO.add(orderDTO) ; 
		}
		return orderListDTO;
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
	@Override
	public String UpdateProduct(ProductRequest product) {
		Product productDataBase = productRepo.findById(product.getId()).orElse(null)  ; 
		productDataBase = model.map(product,Product.class) ; 
		Categories categoriesDataBase = categoriesRepo.findByName(product.getCategories() ); 
		if(categoriesDataBase == null)
		{
			categoriesDataBase = new Categories()  ; 
			categoriesDataBase.setName(product.getCategories());
			categoriesRepo.save(categoriesDataBase)  ; 
		}
		productDataBase.setCategories(categoriesDataBase);
		Brand brandDataBase = brandRepo.findByName(product.getBrand()) ; 
		if(brandDataBase == null)
		{
			brandDataBase = new Brand()  ; 
			brandDataBase.setName(product.getBrand());
			brandRepo.save(brandDataBase)  ; 
		}
		productDataBase.setBrand(brandDataBase); 
		productRepo.save(productDataBase) ;
		return  "done"; 
	}
	@Override
	public String delProduct(long id) {
		List<ProductImage> imageDataBase = productRepo.getListImage(id)  ; 
		for(ProductImage image : imageDataBase)
		{
			cloudinary.deleteImageByUrl(image.getImageUrl()) ; 
		}
		productRepo.deleteById(id);
		return "da xoa";
	}

}
