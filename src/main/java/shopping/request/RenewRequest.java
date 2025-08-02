package shopping.request;


public class RenewRequest {

	private String content ;
	private int rating ; 
	
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	private UserRequest user ;  
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public UserRequest getUser() {
		return user;
	}
	public void setUser(UserRequest user) {
		this.user = user;
	} 
	
}
