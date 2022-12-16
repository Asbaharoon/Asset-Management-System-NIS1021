package com.nissan.util;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.nissan.common.AccessDeniedException;
import com.nissan.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {

	//secret key
	private static String secret="This_is_Nissan";
	//Expiration Time
	private static long expiryDuration=60*60; 
	//Generate Token: Header.payload.signature
	public String generateJwt(User user) {
		long milliTime = System.currentTimeMillis();
		long expiryTime = milliTime + expiryDuration * 1000;
		
		//set IssuedTime and ExpiryTime
		Date issuedAt =new Date(milliTime);
		Date expiryAt =new Date(expiryTime);
		
		//Claims 
		Claims claims = Jwts.claims().setIssuer(user.getId().toString())
				.setIssuedAt(issuedAt)
				.setExpiration(expiryAt);
		//generate jwt using clims
		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secret).compact();
	}
	//AccessDenied for climes
	
	public Claims verify(String authorization) throws Exception{
		try {
			Claims claims=Jwts.parser().setSigningKey(secret).parseClaimsJws(authorization).getBody();
			return claims;
		}catch(Exception e){
			throw new AccessDeniedException("Sorry ! Access Denied ");
		}
	}
}
