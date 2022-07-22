package com.example.SpringbootApi.utility;

import java.util.Calendar;
import java.util.Optional;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {
	
	private static final String KEY = "JWTUTIL_TOKEN_SECURITY_SIGN_KEYS";
	private static final java.security.Key SECURITY_KEY = Keys.hmacShaKeyFor(KEY.getBytes());
    private static final String SYS = "SYS";

    public static String generateToken(String username) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 3);

        Claims claims = Jwts.claims();	
        claims.put("username", username);
        claims.setExpiration(calendar.getTime());
        claims.setIssuer(SYS);
        
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SECURITY_KEY)
                .compact();
    }
    
    public static String getUsernameFromToken(String token) {
		JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(SECURITY_KEY).build();
		Claims claims = jwtParser.parseClaimsJws(token).getBody();
		String username = Optional.ofNullable(claims.get("username")).map(Object::toString).orElse("");
		return username;
    }
    
}
