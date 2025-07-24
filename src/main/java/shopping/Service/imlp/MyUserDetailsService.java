package shopping.Service.imlp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import shopping.Repository.UserRepo;
import shopping.Repository.Entity.User;
import shopping.model.UserPrincal;
@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	UserRepo userRepo ;  
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByName(username) ; 
		if(user == null ) 
		{
			throw new UsernameNotFoundException("khong tim thay") ; 
		}
		return new UserPrincal(user);
	}
	
}
