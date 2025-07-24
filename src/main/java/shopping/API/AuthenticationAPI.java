package shopping.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import shopping.Service.Authentication;
import shopping.model.resultUser;
import shopping.request.UserRequest;

@RestController
public class AuthenticationAPI {
	@Autowired
	Authentication service  ; 
	@PostMapping("/api/register")
	public resultUser register(@RequestBody UserRequest user)
	{
		return service.register(user) ; 
	}
	@PostMapping("/api/login")
	public resultUser login(@RequestBody UserRequest user) 
	{
		return service.login(user) ;  
	}
}
