package shopping.Service.imlp;

import java.io.IOException;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import shopping.Repository.BasketItemRepo;

import shopping.Repository.BrandRepo;
import shopping.Repository.CategoriesRepo;
import shopping.Repository.OrderDetailsRepo;
import shopping.Repository.OrderRepo;
import shopping.Repository.ProductImageRepo;
import shopping.Repository.ProductRepo;
import shopping.Repository.RenewRepo;
import shopping.Repository.ShippingRepo;
import shopping.Repository.StatusRepo;
import shopping.Repository.UserRepo;
import shopping.Repository.Entity.Basket;
import shopping.Repository.Entity.BasketItem;
import shopping.Repository.Entity.Brand;
import shopping.Repository.Entity.Categories;
import shopping.Repository.Entity.OrderDetails;
import shopping.Repository.Entity.Orders;
import shopping.Repository.Entity.Product;
import shopping.Repository.Entity.ProductImage;
import shopping.Repository.Entity.Renew;
import shopping.Repository.Entity.Shipping;
import shopping.Repository.Entity.Status;
import shopping.Repository.Entity.User;
import shopping.Service.JWTService;
import shopping.Service.ProductService;
import shopping.exception.DuplicateUserException;
import shopping.model.resultBasket;
import shopping.model.resultBasketItem;
import shopping.model.resultBrand;
import shopping.model.resultCategories;
import shopping.model.resultOrderDetails;
import shopping.model.resultOrders;
import shopping.model.resultProduct;
import shopping.model.resultShipping;
import shopping.model.resultStatus;
import shopping.request.BasketItemRequest;
import shopping.request.BrandRequest;
import shopping.request.CategoriesRequest;

import shopping.request.OrdersRequest;
import shopping.request.ProductRequest;
import shopping.request.RenewRequest;

@Service
public class ProductServiceIMPL implements ProductService {
		@Autowired
		private ProductRepo productRepo ;
		@Autowired
		private ModelMapper model ;
		@Autowired
		private CategoriesRepo  categoriesRepo ;
		@Autowired
		private BrandRepo brandRepo ; 
		@Autowired
		private JWTService jwt ; 
		@Autowired
		private UserRepo userRepo   ; 
		@Autowired
		private BasketItemRepo basketItemRepo ; 
		@Autowired
		private OrderDetailsRepo orderDetailRepo ;
		@Autowired 
		private OrderRepo orderRepo ; 
		@Autowired
		private ShippingRepo shipRepo ; 
		@Autowired
		private StatusRepo statusRepo ; 
		@Autowired
		private RenewRepo renewRepo ; 
		@Autowired
		private Cloudinary cloudinary ; 
		@Autowired
		private ProductImageRepo productImageRepo ; 
		@Override
		public List<resultProduct> getAllProduct() {
		List<Product> productDataBase = productRepo.findAll() ; 
		List<resultProduct> arr_product = new ArrayList<>() ;
		for(Product item : productDataBase) 
		{
			resultProduct product = model.map(item, resultProduct.class) ; 
			arr_product.add(product) ; 
		}
		return arr_product ; 
		}
		@Override
		public List<resultBrand> getAllBrand() {
			List<Brand> BrandDataBase = brandRepo.findAll() ; 
			List<resultBrand> arr_Brand = new ArrayList<>() ;
			for(Brand item : BrandDataBase) 
			{
				resultBrand brand = model.map(item, resultBrand.class) ; 
				arr_Brand.add(brand) ; 
			}
			return arr_Brand ;  
		}
		@Override
		public List<resultCategories> getAllCategories() {
			List<Categories> CategoriesDataBase = categoriesRepo.findAll() ; 
			List<resultCategories> arr_Categories = new ArrayList<>() ;
			for(Categories item : CategoriesDataBase) 
			{
				resultCategories Categories = model.map(item, resultCategories.class) ; 
				arr_Categories.add(Categories) ; 
			}
			return arr_Categories ;  
		}
		@Override
		@Transactional
		public resultProduct AddProduct(ProductRequest product , List<MultipartFile> fileList) throws IOException {
			Product productDataBase = model.map(product,Product.class) ; 
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
			for(MultipartFile file : fileList)
			{
				Map uploadResult = cloudinary.uploader().upload(file.getBytes(),ObjectUtils.emptyMap() )  ; 
				String imageURL = (String) uploadResult.get("secure_url") ; 
				ProductImage productImage = new ProductImage()  ; 
				productImage.setImageUrl(imageURL);
				productImage.setProduct(productDataBase);
				productImageRepo.save(productImage) ; 
				
			} 
			 
			resultProduct productDTO = model.map(product, resultProduct.class) ; 
			productDTO.setId(productDataBase.getId()); 
			return  productDTO; 
		}
		@Override
		public resultBrand addBrand(BrandRequest brand) {
			Brand brandDataBase = model.map(brand, Brand.class) ; 
			brandRepo.save(brandDataBase) ; 
			resultBrand brandDTO = model.map(brand,resultBrand.class) ;
			brandDTO.setId(brandDataBase.getId());
			return brandDTO ;  
		}
		@Override
		public resultCategories addCategories(CategoriesRequest categories) {
			Categories categoriesDataBase = model.map(categories, Categories.class) ; 
			categoriesRepo.save(categoriesDataBase) ; 
			resultCategories CategoriesDTO = model.map(categories, resultCategories.class) ; 
			CategoriesDTO.setId(categoriesDataBase.getId());
			return CategoriesDTO;
		}
		@Override
		public resultProduct getDetailProduct(long id) {
			Optional<Product> productDataBase = productRepo.findById(Long.valueOf(id)) ; 	
			resultProduct productDTO = model.map(productDataBase, resultProduct.class) ;
			return productDTO ; 
		}
		@Override
		public List<resultProduct> getProductsByCategories(CategoriesRequest categories) {
			Categories categoriesDataBase = categoriesRepo.findByName(categories.getName()) ; 
			List<Product> productDataBase = categoriesDataBase.getProduct() ;
			List<resultProduct> productDTO = new ArrayList<>() ; 
			for(Product item : productDataBase) 
			{
				resultProduct product = model.map(item, resultProduct.class) ; 
				productDTO.add(product) ; 
			}
			return productDTO;
		}
		@Override
		public List<resultProduct> getProductsByBrand(BrandRequest brand) {
			Brand brandDataBase = brandRepo.findByName(brand.getName()) ; 
			List<Product> productDataBase = brandDataBase.getProduct() ;
			List<resultProduct> productDTO = new ArrayList<>() ; 
			for(Product item : productDataBase) 
			{
				resultProduct product = model.map(item, resultProduct.class) ; 
				productDTO.add(product) ; 
			}
			return productDTO;
		}
		@Override
		public String addBasket(long id , String token ) {
			boolean check = false   ; 
			String username = jwt.extractUserName(token)  ;
			User userDataBase = userRepo.findByName(username)  ; 
			Product productDataBase = productRepo.findById(id).orElse(null) ;
			Basket basketDataBase = userDataBase.getBasket()  ; 
			for(BasketItem basketItem : basketDataBase.getBasketItem()) 
			{
				Product product = basketItem.getProduct() ; 
				if(product.getId()==id)
				{
					check = true ; 
					basketItem.setQuantity(basketItem.getQuantity() + 1);
					basketItemRepo.save(basketItem)  ; 
					break ; 
					 
				} 
				
			}
			if(!check)
			{
				BasketItem basketItem = new BasketItem()  ; 
				basketItem.setProduct(productDataBase); 
				basketItem.setQuantity(1);
				basketItem.setBasket(basketDataBase);
				basketItemRepo.save(basketItem)  ; 
				
				
			} 
			return "done"  ; 
			
		}
		@Override
		public resultBasket getBasket(String token) {
			String username = jwt.extractUserName(token) ; 
			User userDataBase = userRepo.findByName(username)  ; 
			Basket basketDataBase = userDataBase.getBasket()  ; 
			List<BasketItem> basketItemDataBase = basketDataBase.getBasketItem() ;
			List<resultBasketItem> basketItemDTO_list = new ArrayList<>()  ; 
			
			for(BasketItem basketItem : basketItemDataBase )
			{
				Product product = basketItem.getProduct() ; 
				resultProduct productDTO = model.map(product, resultProduct.class)  ; 
				resultBasketItem basketItemDTO  =  model.map(basketItem, resultBasketItem.class)  ; 
				basketItemDTO.setProduct(productDTO);
				basketItemDTO_list.add(basketItemDTO) ; 
 			}
			resultBasket basketDTO = model.map(basketDataBase, resultBasket.class)  ; 
			basketDTO.setBasketItem(basketItemDTO_list);
			return basketDTO;
		}
		@Override
		public String delBasket(long id) {
			// TODO Auto-generated method stub
			basketItemRepo.deleteById(id);
			return "da xoa";
		}
		@Override
		public String UpdateBasket(BasketItemRequest basketItem) {
			BasketItem basketItemDataBase = basketItemRepo.findById(basketItem.getId()).orElse(null)  ;
			if(basketItem.getQuatity()<=0) 
			{
				basketItemRepo.deleteById(basketItem.getId()); 
			}
			else 
			{
				basketItemDataBase.setQuantity(basketItem.getQuatity());
				basketItemRepo.save(basketItemDataBase) ; 
			}
			
			
			return "done" ;
		}
		@Override
		@Transactional
		public String BuyProductToBasket( String token, OrdersRequest orderDetails ) {
			long total = 0 ; 
			
			
			String username = jwt.extractUserName(token) ; 
			User userDataBase = userRepo.findByName(username)  ; 
			Orders orderDataBase = new Orders()  ;
			List<BasketItem> basketItemList = userRepo.findBasketItemByuser(userDataBase.getId()) ;
			if(basketItemList.size()==0)
			{
				throw new DuplicateUserException("gio hang dang trong") ; 
			}
			Shipping shipping = model.map(orderDetails.getShipping(), Shipping.class) ; 
			shipping.setIsDelivery(false);
			shipRepo.save(shipping) ;
			orderDataBase.setUser(userDataBase); 
			orderDataBase.setOrder_Date(LocalDate.now());
			orderDataBase.setShipping(shipping); 
			orderDataBase.setNote(orderDetails.getNote());
			Status statusDataBase = statusRepo.findByName("pending") ; 
			if(statusDataBase==null)
			{
				statusDataBase= new Status() ; 
				statusDataBase.setName("pending");
				statusDataBase.setColor("pending");
				statusRepo.save(statusDataBase)  ; 
			}
			orderDataBase.setStatus(statusDataBase);
			orderRepo.save(orderDataBase)  ; 
			 
			for(BasketItem basketItem : basketItemList) 
			{
				OrderDetails orderDetailDataBase = new OrderDetails()  ; 
				orderDetailDataBase.setProduct(basketItem.getProduct());
				orderDetailDataBase.setQuantity((int)basketItem.getQuantity()); 
				orderDetailDataBase.setOrder(orderDataBase); 
				orderDetailRepo.save(orderDetailDataBase) ; 
				total = total + basketItem.getQuantity()*basketItem.getProduct().getPrice() ; 
				
				Product productDataBase = basketItem.getProduct() ; 
				long newQuatity = productDataBase.getQuantity() -  basketItem.getQuantity() ; 
				if(newQuatity > 0)
				{
					productDataBase.setQuantity(newQuatity);
					productRepo.save(productDataBase) ; 
				}
				else
				{
					productRepo.deleteById(productDataBase.getId());
				}
			}
			basketItemRepo.deleteAll(basketItemList);
			orderDataBase.setTotal_price(total);
			orderRepo.save(orderDataBase)  ; 
			return "da mua hang " ; 
		}
		@Override
		public String BuyNow(String token, OrdersRequest ordersRequest, long id) {
			String username = jwt.extractUserName(token) ; 
			User userDataBase = userRepo.findByName(username)  ; 
			Product productDataBase = productRepo.findById(id).orElse(null)  ;
			OrderDetails orderDetailsDataBase  = model.map(ordersRequest.getOrderDetails(), OrderDetails.class)  ;  
			orderDetailsDataBase.setProduct(productDataBase);
			
			Orders orderDataBase = model.map(ordersRequest, Orders.class) ; 
			orderDataBase.setUser(userDataBase);
			Shipping shippingDataBase = model.map(ordersRequest.getShipping(), Shipping.class) ; 
			shippingDataBase.setIsDelivery(false);
			shipRepo.save(shippingDataBase)  ; 
			orderDataBase.setShipping(shippingDataBase);
			long total =   ordersRequest.getOrderDetails().getQuantity()*productDataBase.getPrice() ; 
			orderDataBase.setTotal_price(total); 
			orderDataBase.setOrder_Date(LocalDate.now());
			
			Status statusDataBase = statusRepo.findByName("pending") ; 
			if(statusDataBase==null)
			{
				statusDataBase = new Status() ; 
				statusDataBase.setName("pending");
				statusRepo.save(statusDataBase)  ; 
			}
			orderDataBase.setStatus(statusDataBase);
			orderRepo.save(orderDataBase) ; 
			orderDetailsDataBase.setOrder(orderDataBase);
			orderDetailRepo.save(orderDetailsDataBase)  ; 
			return "da mua san pham"  ; 
			
		}
		@Override
		public List<resultOrders> getOrder(String token) {
			String username = jwt.extractUserName(token) ; 
			User user = userRepo.findByName(username)  ; 
			List<Orders> ordersDataBase = user.getOrders() ; 
			List<resultOrders> ordersDTO = new ArrayList<>()  ; 
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
				ordersDTO.add(item) ; 
			}
			return ordersDTO;
		}
		@Override
		public String addRenew(RenewRequest renewRequest, String token , long id ) {
			String username =  jwt.extractUserName(token) ; 
			User userDataBase = userRepo.findByName(username) ; 
			Product productDataBase = productRepo.findById(id).orElse(null) ;
 			boolean checkProductInUser = userRepo.checkProductInUser(userDataBase.getId(), id) ; 
 			boolean checkRenewInUser = userRepo.checkRenewInUser(userDataBase.getId(), id) ; 
 			if(!checkProductInUser) 
 			{
 				throw new DuplicateUserException("ban chua mua san pham nay")  ; 
 			}
 			else if(checkRenewInUser)
 			{
 				throw new DuplicateUserException("ban da binh luan o day roi")  ; 
 			}
			Renew renewDataBase = new Renew()  ; 
			renewDataBase.setContent(renewRequest.getContent());
			renewDataBase.setUser(userDataBase);
			renewDataBase.setProduct(productDataBase);
			renewRepo.save(renewDataBase)  ; 
			return "da them binh luan cua ban vao roi " ; 
		}
	}
	

