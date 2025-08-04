package shopping.API;

import java.io.IOException;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import shopping.Service.AdminService;
import shopping.Service.ProductService;
import shopping.model.ResultListNameOrder;
import shopping.model.resultBrand;
import shopping.model.resultCategories;
import shopping.model.resultOrders;
import shopping.model.resultProduct;
import shopping.model.resultUser;
import shopping.model.resultUserDetail;
import shopping.request.BrandRequest;
import shopping.request.CategoriesRequest;
import shopping.request.ProductRequest;
import shopping.request.RoleRequest;

@RestController
@CrossOrigin(origins = "*")

public class APIAdmin {
	@Autowired
	AdminService serviceAdmin ; 
	@Autowired
	ProductService service; 
	
	// quan ly nguoi dung 
	@GetMapping("/api/admin/getUser")
	public List<resultUser> getUser()
	{
		return serviceAdmin.getUser() ; 
	}
	@GetMapping("/api/admin/getUser/{id}")
	public resultUserDetail getUserDetail(@PathVariable long id ) 
	{
		return serviceAdmin.getUserDetail(id) ; 
	}
	@PostMapping("/api/admin/changeRoleUser/{id}")
	public String changeRoleUser(@RequestBody RoleRequest roleRequest , @PathVariable long id ) 
	{
		return serviceAdmin.changeRoleUser(roleRequest, id) ; 
	}
	@DeleteMapping("/api/admin/deleteUser/{id}")
	public String deleteUser(@PathVariable long id) 
	{
		return serviceAdmin.deleteUser(id) ; 
	}
	// them sua xoa san pham, nhan hang , danh muc 
	@PostMapping(value="/api/admin/addProduct",consumes = MediaType.MULTIPART_FORM_DATA_VALUE) 
	public resultProduct addProduct(@RequestPart ProductRequest dataProduct, @RequestPart List<MultipartFile> fileList ) throws IOException
	{
		return service.AddProduct(dataProduct,fileList) ; 
	}
	@PostMapping("/api/admin/addBrand") 
	public resultBrand addBrand(@RequestBody BrandRequest data)
	{
		return service.addBrand(data) ; 
	}
	@PatchMapping("/api/admin/updateBrand")
	public String updateBrand(@RequestBody BrandRequest brand)
	{
		return service.updateBrand(brand) ; 
	}
	@DeleteMapping("/api/admin/delBrand/{id}")
	public String delBrand(@PathVariable("id") long id)
	{
		return service.delBrand(id)  ; 
	}
	@PostMapping("/api/admin/addCategories") 
	public resultCategories addCategories(@RequestBody CategoriesRequest data)
	{
		
		return service.addCategories(data) ;  
	}
	@PatchMapping("/api/admin/updateCategories")
	public String updateBrand(@RequestBody CategoriesRequest categories)
	{
		return service.updateCategories(categories) ; 
	}
	@DeleteMapping("/api/admin/delCategories/{id}")
	public String delCategories(@PathVariable("id") long id)
	{
		return service.delCategories(id)  ; 
	}
	@PatchMapping("/api/admin/updateProduct")
	public String updateProduct(@RequestBody ProductRequest product)
	{
		return serviceAdmin.UpdateProduct(product) ; 
	}
	@DeleteMapping("/api/admin/delProduct/{id}")
	public String delProduct(@PathVariable("id") long id)
	{
		return serviceAdmin.delProduct(id) ;
	}
	// quan ly don hang 
	@GetMapping("/api/admin/listUser/status/{nameStatus}")
	public List<resultOrders> getListOrderUser(@PathVariable String nameStatus)
	{
		return serviceAdmin.getListOrder(nameStatus) ;
	}
	@GetMapping("/api/admin/listOrder/status")
	public List<resultOrders> getListOrder(@RequestParam("nameStatus") String nameStatus, @RequestParam("id") long id)
	{
		return serviceAdmin.getListOrder(nameStatus, id) ; 
	}
	@PostMapping("/api/admin/changeStatus")
	public String changeStatusOrder(@RequestParam("nameStatus") String nameStatus, @RequestParam("id") long id)
	{
		return serviceAdmin.changeStatusOrder(nameStatus, id) ; 
	}
	
	// thong ke 
	@GetMapping("/api/admin/statiscal/{nameStatus}")
	public int  statiscalTotal(@PathVariable("nameStatus") String nameStatus)
	{
		return serviceAdmin.totalStatiscal(nameStatus) ; 
	}
	@GetMapping("/api/admin/statiscal")
	public int statusTotalMonth(@RequestParam("month") int month , @RequestParam("nameStatus") String nameStatus)
	{
		return serviceAdmin.totalStatiscalMonth(nameStatus, month) ; 
	}
}
