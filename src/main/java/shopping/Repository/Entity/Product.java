package shopping.Repository.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	long id ; 
	
	@Column(name = "name")
	String name ; 
	@Column(name="quantity")
	long quantity ; 
	@Column(name ="description")
	String description ; 
	@Column(name =  "color")
	String color ; 
	@Column(name ="price")
	long price ; 
	@Column(name= "discount")
	long discount ; 
	@Column(name ="RAM")
	String RAM ; 
	@Column(name ="screen")
	String screen ; 
	@Column(name ="gpu")
	String gpu ; 
	@Column(name ="cpu")
	String cpu ; 
	@Column(name ="driver_size")
	String driver_size ; 
	@Column(name ="count_camera")
	int  count_camera ;
	@Column(name="resolution")
	String resolution ; 
	@Column(name = "sensor")
	String sensor ; 
	@Column(name = "capacity_battery")
	long capacity_battery ; 
	@Column(name ="operating_system")
	String operating_system ; 
	@Column(name = "connectivity")
	String connectivity ; 
	@Column(name ="audio_technical")
	String audio_technical ; 
	@Column(name = "style")
	String style ; 
	@Column(name = "time_battery")
	long time_battery ; 
	@Column(name ="delay")
	double delay  ; 
	@Column(name = "support_stylus")
	boolean suport_stylus ;
	@OneToMany(mappedBy="product",cascade = CascadeType.ALL)
	private List<BasketItem> basketItem ;
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name ="brand_id")
	private Brand brand; 
	@ManyToOne(cascade=CascadeType.ALL) 
	@JoinColumn(name="categories_id")
	private Categories categories ;
	@OneToMany(mappedBy = "product" , cascade =CascadeType.ALL)
	private List<OrderDetails> OrderDetails ;
	@OneToMany(mappedBy="product",cascade = CascadeType.ALL)  
	private List<Renew> renew ;
	@OneToMany(mappedBy = "product" ,cascade = CascadeType.ALL) 
	private List<ProductImage> imageList  ; 
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public long getDiscount() {
		return discount;
	}
	public void setDiscount(long discount) {
		this.discount = discount;
	}
	public String getRAM() {
		return RAM;
	}
	public void setRAM(String rAM) {
		RAM = rAM;
	}
	public String getScreen() {
		return screen;
	}
	public void setScreen(String screen) {
		this.screen = screen;
	}
	public String getGpu() {
		return gpu;
	}
	public void setGpu(String gpu) {
		this.gpu = gpu;
	}
	public String getCpu() {
		return cpu;
	}
	public void setCpu(String cpu) {
		this.cpu = cpu;
	}
	public String getDriver_size() {
		return driver_size;
	}
	public void setDriver_size(String driver_size) {
		this.driver_size = driver_size;
	}
	public int getCount_camera() {
		return count_camera;
	}
	public void setCount_camera(int count_camera) {
		this.count_camera = count_camera;
	}
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public String getSensor() {
		return sensor;
	}
	public void setSensor(String sensor) {
		this.sensor = sensor;
	}
	public long getCapacity_battery() {
		return capacity_battery;
	}
	public void setCapacity_battery(long capacity_battery) {
		this.capacity_battery = capacity_battery;
	}
	public String getOperating_system() {
		return operating_system;
	}
	public void setOperating_system(String operating_system) {
		this.operating_system = operating_system;
	}
	public String getConnectivity() {
		return connectivity;
	}
	public void setConnectivity(String connectivity) {
		this.connectivity = connectivity;
	}
	public String getAudio_technical() {
		return audio_technical;
	}
	public void setAudio_technical(String audio_technical) {
		this.audio_technical = audio_technical;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public long getTime_battery() {
		return time_battery;
	}
	public void setTime_battery(long time_battery) {
		this.time_battery = time_battery;
	}
	public double getDelay() {
		return delay;
	}
	public void setDelay(double delay) {
		this.delay = delay;
	}
	public boolean isSuport_stylus() {
		return suport_stylus;
	}
	public void setSuport_stylus(boolean suport_stylus) {
		this.suport_stylus = suport_stylus;
	}
	
	public List<BasketItem> getBasketItem() {
		return basketItem;
	}
	public void setBasketItem(List<BasketItem> basketItem) {
		this.basketItem = basketItem;
	}
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public Categories getCategories() {
		return categories;
	}
	public void setCategories(Categories categories) {
		this.categories = categories;
	}
	public List<OrderDetails> getOrderDetails() {
		return OrderDetails;
	}
	public void setOrderDetails(List<OrderDetails> orderDetails) {
		OrderDetails = orderDetails;
	}
	public List<Renew> getRenew() {
		return renew;
	}
	public void setRenew(List<Renew> renew) {
		this.renew = renew;
	} 
	
	
}
