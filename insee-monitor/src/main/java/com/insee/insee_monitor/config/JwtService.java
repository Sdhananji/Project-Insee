package com.insee.insee_monitor.config;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

import org.springframework.beans.factory.annotation.Value;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String SECRET_KEY;

    private Key getSignKey(){
        return  Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String extractEmail(String token){
        return extractClaim(token,Claims::getSubject);
    }
    public <T> T extractClaim(String token, Function <Claims, T> claimsResolver){
        final Claims claims=extractAllClaims(token);
        return claimsResolver.apply(claims);

    }
    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
            .setSigningKey(getSignKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }
    public String generateToken(String email, String role){
        Map<String,Object>claims=new HashMap<>();
        claims.put("role",role);
        return createToken(claims,email);
    }
    private String createToken(Map<String,Object> claims, String subject){
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis()+1000*60*60)) //1 hour
            .signWith(getSignKey(),SignatureAlgorithm.HS256)
            .compact();
        }

        public boolean isTokenValid(String token, String email){
            final String extractEmail=extractEmail(token);
            return (email.equals(extractEmail) && !isTokenExpired(token));
        }

        private boolean isTokenExpired(String token){
            return extractClaim(token, Claims::getExpiration).before(new Date());
        }
    
}
