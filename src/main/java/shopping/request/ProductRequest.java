package shopping.request;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class ProductRequest {
	String name ; 

	long quantity ; 

	String description ; 

	String color ; 

	long price ; 

	long discount ; 

	String RAM ; 

	String screen ; 

	String gpu ; 

	String cpu ; 

	String driver_size ; 

	int  count_camera ;

	String resolution ; 

	String sensor ; 
	long capacity_battery ; 

	String operating_system ; 

	String connectivity ; 

	String audio_technical ; 

	String style ; 

	long time_battery ; 

	double delay  ; 

	boolean suport_stylus ;


	private String brand; 


	private String categories ;
	private List<MultipartFile> images ; 

	
	
	public List<MultipartFile> getImages() {
		return images;
	}
	public void setImages(List<MultipartFile> images) {
		this.images = images;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getCategories() {
		return categories;
	}
	public void setCategories(String categories) {
		this.categories = categories;
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
	
}
