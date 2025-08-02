package shopping.API;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import shopping.Service.ProductService;
import shopping.Service.UserService;
import shopping.model.resultBasket;
import shopping.model.resultOrders;
import shopping.model.resultProduct;
import shopping.model.resultUser;
import shopping.request.BasketItemRequest;
import shopping.request.OrderDetailsRequest;
import shopping.request.OrdersRequest;
import shopping.request.RenewRequest;
import shopping.request.UserRequest;
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserAPI {
	@Autowired
	private ProductService service ;  
	@Autowired
	private UserService userService; 
 // them san pham vao gio hang
	@PostMapping("/api/user/addBasket/{id}") 
	public String addBasket(@PathVariable long id, @RequestHeader("Authorization") String authen ) 
	{
		String token = authen.replace("Bearer ","")  ; 
		
		return service.addBasket(id, token); 
	}
	// xem danh sach gio hang cua user
	@GetMapping("/api/user/getBasket")
	public resultBasket getBasket(@RequestHeader("Authorization") String authen)
	{ 
		String token = authen.replace("Bearer ","")  ; 
		return service.getBasket(token) ; 
	}
	// xoa san pham khoi gio hang 
	@DeleteMapping("/api/user/delBasket/{id}")
	public String delBasket(@PathVariable long id )
	{
		return service.delBasket(id) ; 
	}
	// cap nhat  so luong trong gio hang 
	@PatchMapping("api/user/updateBasket") 
	public String updateBasket(@RequestBody BasketItemRequest basketItem)
	{
		return service.UpdateBasket(basketItem) ; 
	}
	// mua hang tu gio hang 
	@PostMapping("/api/user/buyBasket")
	public String BuyProductToBasket( @RequestHeader("Authorization") String autho ,@RequestBody OrdersRequest orderDetails  ) 
	{
		String token = autho.replace("Bearer ","")  ; 
		
		return service.BuyProductToBasket(token, orderDetails) ; 
	}
	// mua ngay 
	@PostMapping("/api/user/buyNow/{id}")
	public String BuyNow(@RequestBody OrdersRequest ordersRequest , @RequestHeader("Authorization") String autho , @PathVariable long id )
	{
		String token = autho.replace("Bearer ","")  ; 
		return service.BuyNow(token, ordersRequest, id) ; 
	}
	// xem  danh sach gio hang 
	@GetMapping("/api/user/getOrder")
	public List<resultOrders> getOrder(@RequestHeader("Authorization") String autho)  
	{
		String token = autho.replace("Bearer ","") ; 
		return service.getOrder(token) ;
	}
	// danh gia san pham 
	@PostMapping("/api/user/addRenew/{id}")
	public String addRenew(@RequestBody RenewRequest renewRequest , @RequestHeader("Authorization") String autho ,@PathVariable long id )
	{
		String token = autho.replace("Bearer ", "")  ; 
		return service.addRenew(renewRequest, token, id) ;
	}
	// xem thong tin 
	@GetMapping("/api/user/getProfile") 
	public resultUser getProfile(@RequestHeader("Authorization") String autho)
	{
		String token = autho.replace("Bearer ", "") ; 
		return userService.getProfile(token) ; 
	}
	// xem trang thai don hang
	@GetMapping("/api/user/order/status/{status}")
	public List<resultOrders> getStatusOrderUser(@PathVariable("status") String status , @RequestHeader("Authorization") String autho)
	{
		String token = autho.replace("Bearer ", "") ; 
		return userService.getStatusOrderUser(status, token) ;
	}
	//xem chi tiet don hang
	@GetMapping("/api/user/getDetailOrder/{id}") 
	public resultOrders getDetailOrder(@PathVariable("id") long id)
	{
		return userService.getDetailOrder(id) ; 
	}
	// thay doi thon tin 
	@PatchMapping("/api/user/updateInfor")
	public String updateUser(@RequestHeader("Authorization") String autho , @RequestBody UserRequest userRequest)
	{
		String token = autho.replace("Bearer ", "") ; 
		return userService.updateInfor(token, userRequest) ;
	} 
}
