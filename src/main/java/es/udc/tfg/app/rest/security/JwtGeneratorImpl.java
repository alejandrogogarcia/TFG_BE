package es.udc.tfg.app.rest.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtGeneratorImpl implements JwtGenerator{

	@Value("${jwt.secretKey}")
	private String secretKey;
	
	@Value("${jwt.validity}")
	private long validity;
	
	@Override
	public String generate(JwtInfo info) {
		
		return Jwts.builder()
				.claim("userId", info.getUserId())
				.claim("role", info.getRole())
				.setExpiration(new Date(System.currentTimeMillis() + validity*60*1000))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes())
				.compact();
	}

	@Override
	public JwtInfo getInfo(String token) {
		
		Claims claims = Jwts.parser()
		        .setSigningKey(secretKey.getBytes())
		        .parseClaimsJws(token)
		        .getBody();
			
			return new JwtInfo(
				((Integer) claims.get("userId")).longValue(), 
				claims.getSubject(), 
				(String) claims.get("role"));
	}

}
