package shopping.Service;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.annotation.PostConstruct;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import shopping.Repository.Entity.User;

@Service
public class JWTService {
    
    @Value("${JWT_SECRET:}")
    private String jwtSecret;
    
    private String chiakhoa = "";
    
    @PostConstruct
    public void init() throws NoSuchAlgorithmException {
        if (jwtSecret != null && !jwtSecret.isEmpty()) {
          
            chiakhoa = jwtSecret;
            System.out.println("Using JWT_SECRET from environment variable");
        } else {
        
            KeyGenerator keygen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey skey = keygen.generateKey();
            chiakhoa = Base64.getEncoder().encodeToString(skey.getEncoded());
            System.out.println("Generated new JWT secret key for local development");
        }
    }
    
    public String getToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", user.getRole().getName());
        
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getName())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24 hours
                .signWith(getKey())
                .compact();
    }
    
    private Key getKey() {
        byte[] keyByte = Decoders.BASE64.decode(chiakhoa);
        return Keys.hmacShaKeyFor(keyByte);
    }
    
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    
    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }
    
    public Claims extractAllClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            System.err.println("JWT parsing error: " + e.getMessage());
            throw e;
        }
    }
    
    public boolean validateToken(String token, UserDetails userDetails) {
        try {
            final String userName = extractUserName(token);
            return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
        } catch (Exception e) {
            System.err.println("JWT validation error: " + e.getMessage());
            return false;
        }
    }
    
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}