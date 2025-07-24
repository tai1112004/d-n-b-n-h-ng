package shopping.Service;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import shopping.Repository.Entity.User;

@Service
public class JWTService {
private String chiakhoa = "" ;
	
	public JWTService() throws NoSuchAlgorithmException {
		KeyGenerator keygen = KeyGenerator.getInstance("HMACSHA256") ; 
		SecretKey skey = keygen.generateKey() ; 
		chiakhoa = Base64.getEncoder().encodeToString(skey.getEncoded()) ;
	}
	public String getToken(User user )
	{
		Map<String,String> claims = new HashMap<>() ;
//		for(vaitro role : username.getDs_vaitro())
//		{
//			System.out.println("------------------------------------------------------------------");
//			System.out.println(role.getName());
//			System.out.println("------------------------------------------------------------------");
//		}
//		System.out.println("ditconme lai la taoooooooo: " + username.getDs_vaitro().size());
//		for(vaitro item:username.getDs_vaitro())
//		{
//			claims.put("scope",BuilRole(username)) ;
//		}
		claims.put("roles", user.getRole().getName()) ; 
		return    Jwts.builder()
					.setClaims(claims)
					.setSubject(user.getName())
					.setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(new Date(System.currentTimeMillis()+ 60*60*60 ))
					.signWith(getKey())
					.compact() ; 
				
				
				
				
				
				
	}

	private Key getKey() {
		byte[] keyByte = Decoders.BASE64.decode(chiakhoa) ; 
		return Keys.hmacShaKeyFor(keyByte);
	}
	public String extractUserName(String token) {
        // extract the username from jwt token
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
        		.setSigningKey(getKey())
        		.build().parseClaimsJws(token).getBody() ; 
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
 

}
