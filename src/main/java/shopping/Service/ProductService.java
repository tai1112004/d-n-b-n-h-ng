package shopping.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.multipart.MultipartFile;

import shopping.model.resultBasket;
import shopping.model.resultBrand;
import shopping.model.resultCategories;
import shopping.model.resultOrders;
import shopping.model.resultProduct;
import shopping.model.resultRenew;
import shopping.request.BasketItemRequest;
import shopping.request.BrandRequest;
import shopping.request.CategoriesRequest;
import shopping.request.OrderDetailsRequest;
import shopping.request.OrdersRequest;
import shopping.request.ProductRequest;
import shopping.request.RenewRequest;

public interface ProductService {
	public List<resultProduct> getAllProduct() ; 
	public List<resultProduct> getProductDiscount()  ; 
	public List<resultBrand> getAllBrand() ; 
	public List<resultCategories> getAllCategories() ; 
	public resultProduct  AddProduct(ProductRequest product , List<MultipartFile> file) throws IOException ; 
	public resultBrand addBrand(BrandRequest brand ) ; 
	public resultCategories addCategories(CategoriesRequest categories) ;
	public String updateBrand(BrandRequest brand) ; 
	public String updateCategories(CategoriesRequest categories); 
	public String delBrand(long id )  ; 
	public String delCategories(long id)  ; 
	public resultProduct getDetailProduct(long id) ; 
	public List<resultProduct> getProductsByCategories(String categories ) ; 
	public List<resultProduct> getProductsByBrand(BrandRequest brand) ;
	public String addBasket( long id ,  String token) ; 
	public resultBasket getBasket(String token)  ; 
	public String delBasket(long id)  ; 
	public String UpdateBasket(BasketItemRequest basketItem)  ;
	public String BuyProductToBasket(String token  , OrdersRequest orderDetails )  ; 
	public String BuyNow(String token , OrdersRequest ordersRequest, long id )  ;
	public List<resultOrders> getOrder(String token)  ; 
	public String addRenew(RenewRequest renewRequest ,  String token,long id ) ; 

} 
