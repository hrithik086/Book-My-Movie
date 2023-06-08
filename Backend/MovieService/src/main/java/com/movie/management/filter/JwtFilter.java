package com.movie.management.filter;

import java.io.IOException;
import java.security.Key;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.filter.GenericFilterBean;

import com.movie.management.service.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtFilter extends GenericFilterBean {
	
	private final String SECRET="6A576D5A7134743777217A25432A462D4A614E645267556B5870327235753878";
	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		for(int i=0;i<5;i++) {
			System.out.println("This is JwtFilter of Movie Service");
		}
		
		HttpServletRequest myRequest=(HttpServletRequest) request;
		HttpServletResponse myResponse=(HttpServletResponse) response;
		
		String header=myRequest.getHeader("Authorization");
		
		System.out.println(header);
		
		if(header==null || !header.startsWith(header)) {
			throw new ServletException("Missing header or the header doesn't starts with bearer");
		}
		
		String token=header.substring(7);
		
//		Claims claims= Jwts.parser()
//						   .setSigningKey("fse1")
//						   .parseClaimsJws(token)
//						   .getBody();
		
		Claims claims= Jwts.parserBuilder()
							.setSigningKey(getSecurityKey(SECRET))
							.build()
							.parseClaimsJws(token)
							.getBody();
		
		myRequest.setAttribute("username", claims);
		
		chain.doFilter(request, response);
		
		
	}
	
	private Key getSecurityKey(String secret) {
		byte[] keyBytes=Decoders.BASE64.decode(secret);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}