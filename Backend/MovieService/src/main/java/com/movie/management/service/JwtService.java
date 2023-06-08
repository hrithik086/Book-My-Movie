package com.movie.management.service;
import java.security.Key;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {
	
	private final String SECRET="6A576D5A7134743777217A25432A462D4A614E645267556B5870327235753878";
	
	public String getRole(String token) {
    	return (String)Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getOrDefault("role", "null");
    }
	
	 private Key getSignKey() {
	        byte[] keyBytes= Decoders.BASE64.decode(SECRET);
	        return Keys.hmacShaKeyFor(keyBytes);
	 }
	 
	 public boolean validateToken(String jwtToken) {
		 if(jwtToken!=null && jwtToken.startsWith("Bearer ")) {
			 String token=jwtToken.substring(7);
			 try {
				 Claims claims= Jwts.parserBuilder()
							.setSigningKey(getSecurityKey(SECRET))
							.build()
							.parseClaimsJws(token)
							.getBody();
				 return true;
			 }
			 catch(Exception e) {
				 return false;
			 }
		 }
		 else {
			 return false;
		 }
	 }
	 
	 private Key getSecurityKey(String secret) {
			byte[] keyBytes=Decoders.BASE64.decode(secret);
			return Keys.hmacShaKeyFor(keyBytes);
	}
	
}