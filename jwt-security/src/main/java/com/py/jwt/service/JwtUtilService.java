package com.py.jwt.service;



import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtUtilService {
	  private static final String JWT_SECRET_KEY = "eyJpc3MiOiJqb2UiLA0KICJleHAiOjEzMDA4MTkzODAsDQogImh0dHA6Ly9leGFtcGxlLmNvbS9pc19yb290Ijp0cnVlfQ";

	  //													SEGUNDOS	MINUTOS		 HORAS
	  public static final long JWT_TOKEN_VALIDITY = 1000 	* 60 		* 60 		 * (long) 24;

	  public String extractUsername(String token) {
	    return extractClaim(token, Claims::getSubject);
	  }

	  public Date extractExpiration(String token) {
	    return extractClaim(token, Claims::getExpiration);
	  }

	  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
	    return claimsResolver.apply(extractAllClaims(token));
	  }
	  
	  private Key getSigningKey() {
	      byte[] keyBytes = Decoders.BASE64.decode(JWT_SECRET_KEY);
	      return Keys.hmacShaKeyFor(keyBytes);
	  }
	  
	  private Claims extractAllClaims(String token) {
	      return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
	              .getBody();
	  }

	  private Boolean isTokenExpired(String token) {
	    return extractExpiration(token).before(new Date());
	  }

	  public String generateToken(UserDetails userDetails) {
	    Map<String, Object> claims = new HashMap<>();
	    // Agregando informacion adicional como "claim"
	    claims.put("username", userDetails.getUsername());
	    return createToken(claims, userDetails.getUsername());
	  }

	  private String createToken(Map<String, Object> claims, String subject) {
	    return Jwts
	        .builder()
	        .setClaims(claims)
	        .setSubject(subject)
	        .setIssuedAt(new Date(System.currentTimeMillis()))
	        .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
	        .signWith(getSigningKey(), SignatureAlgorithm.HS256)
	        .compact();
	  }

	  public boolean validateToken(String token, UserDetails userDetails) {
	    final String username = extractUsername(token);
	    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	  }
	  
}
