package shopping.Service.imlp;

import java.io.NotActiveException;
import java.rmi.NotBoundException;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import shopping.Repository.BasketRepo;
import shopping.Repository.RoleRepo;
import shopping.Repository.UserRepo;
import shopping.Repository.Entity.Basket;
import shopping.Repository.Entity.Role;
import shopping.Repository.Entity.User;
import shopping.Service.Authentication;
import shopping.Service.JWTService;
import shopping.exception.DuplicateUserException;
import shopping.model.resultUser;
import shopping.request.UserRequest;
@Service
public class AuthenticationIMP implements Authentication {
	@Autowired
	private UserRepo userRepo ;
	@Autowired
	private RoleRepo roleRepo ; 
	@Autowired
	private JWTService jwtService ;
	@Autowired
	private BasketRepo basketRepo ; 
	private BCryptPasswordEncoder bcyr = new BCryptPasswordEncoder(12) ;
	@Autowired
	ModelMapper  model ; 
	@Override
	public resultUser register(UserRequest user) {
		
		 User userDataBase = userRepo.findByName(user.getName()) ;
		 Role roleDataBase = roleRepo.findByName("USER") ; 
		 if(roleDataBase==null)
		 {
			 
			 roleDataBase= new Role() ; 
			 roleDataBase.setName("USER") ; 
			 
			 roleRepo.save(roleDataBase) ; 
		 }
		 if(userDataBase != null )
		 {
 			   throw new DuplicateUserException("tai khoan da ton tai") ; 
		 }
		 else 
		 {
			 Basket basketDataBase = new Basket() ;
			  userDataBase = model.map(user, User.class) ; 
			 userDataBase.setPassword(bcyr.encode(userDataBase.getPassword())); 
			 userDataBase.setRole(roleDataBase); 
			 userDataBase.setBasket(basketDataBase);
			 basketRepo.save(basketDataBase) ; 
			 userRepo.save(userDataBase) ;
			 resultUser userDTO = model.map(userDataBase, resultUser.class) ; 
			 userDTO.setToken(jwtService.getToken(userDataBase));
			 userDTO.setRole(roleDataBase.getName());
			return userDTO ;
		 }
		
	}
	@Override
	public resultUser login(UserRequest user) {
		User userDataBase = userRepo.findByName(user.getName()) ; 
		if(userDataBase ==null)
		{
			throw new DuplicateUserException("ten tai khoan khong dung") ; 
		}
		else 
		{
			boolean isCheck = bcyr.matches(user.getPassword(),userDataBase.getPassword() ) ; 
			if(!isCheck)
			{
				throw new DuplicateUserException("Sai mat khau roi") ; 
			}
			else 
			{
				resultUser userDTO = model.map(userDataBase, resultUser.class) ; 
				userDTO.setToken(jwtService.getToken(userDataBase)) ; 
				userDTO.setRole(userDataBase.getRole().getName());
 				return userDTO ; 
			}
		}
		
	}

}
