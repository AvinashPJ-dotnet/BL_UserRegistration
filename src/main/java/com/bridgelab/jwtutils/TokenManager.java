package com.bridgelab.jwtutils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenManager implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7008375124389347049L;
	public static final long TOKEN_VALIDITY = 10 * 60 * 60;
	
//	@Value("${secret}")
	private static String jwtSecret="secretkeyexpamle";
	
	public static String generateJwtToken(int userId) {
		
		Map<String, Object> claims =new HashMap<>();
		claims.put("userKey",userId);
		
		System.out.println("secret key"+jwtSecret);
		return Jwts.builder().setClaims(claims)
//				.setIssuedAt(new Date(System.currentTimeMillis()))
//				.setExpiration(new Date(System.currentTimeMillis()+ TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512,jwtSecret).compact();
		
	}
//
//	public boolean validateJwtToken(String token,UserDetails userdetails) {
//		String username=getUsernameFromToken(token);
//		Claims claims=Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
//		Boolean isTokenExpired = claims.getExpiration().before(new Date()); 
//	      return (username.equals(userdetails.getUsername()) && !isTokenExpired); 
//		
//	}
//
	 public static int getUsernameFromToken(String token) {
	      final Map<String, Object> claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody(); 
	      return Integer.parseInt(claims.get("userKey").toString()) ; 
	   }
}
