package shopping.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.annotation.PostConstruct;

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
    
    @Value("${JWT_SECRET}")
    private String jwtSecret;
    
    private String chiakhoa = "";
    
    @PostConstruct
    public void init() {
        System.out.println("=== JWT SERVICE INIT ===");
        System.out.println("Environment: " + (jwtSecret.contains("Railway") ? "RAILWAY" : "LOCAL"));
        
        if (jwtSecret != null && !jwtSecret.isEmpty()) {
            chiakhoa = jwtSecret;
            System.out.println("JWT_SECRET loaded successfully");
            System.out.println("Key length: " + jwtSecret.length() + " characters");
            System.out.println("Key preview: " + jwtSecret.substring(0, 20) + "...");
        } else {
            System.err.println("JWT_SECRET not found in environment!");
            throw new RuntimeException("JWT_SECRET is required!");
        }
        System.out.println("========================");
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
            throw new RuntimeException("Invalid JWT token: " + e.getMessage());
        }
    }
    
    public boolean validateToken(String token, UserDetails userDetails) {
        try {
            final String userName = extractUserName(token);
            boolean isValid = (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
            System.out.println("JWT validation for user '" + userName + "': " + (isValid ? "VALID" : "INVALID"));
            return isValid;
        } catch (Exception e) {
            System.err.println("JWT validation error: " + e.getMessage());
            return false;
        }
    }
    
    private boolean isTokenExpired(String token) {
        Date expiration = extractExpiration(token);
        boolean expired = expiration.before(new Date());
        if (expired) {
            System.out.println("JWT token expired at: " + expiration);
        }
        return expired;
    }
    
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}