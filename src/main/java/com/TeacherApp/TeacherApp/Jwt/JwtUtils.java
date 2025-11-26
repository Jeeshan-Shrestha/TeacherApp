package com.TeacherApp.TeacherApp.Jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.TeacherApp.TeacherApp.Models.Student;
import com.TeacherApp.TeacherApp.Models.Teacher;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecureDigestAlgorithm;

@Component
public class JwtUtils {
    
    private String SECRET_KEY = "asrdftyghuyhjuythtdfgkjdhfgkjhdfgkljhdlkfjghkh";
    private SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();
        if (userDetails instanceof Student){
            Student s = (Student)userDetails;
            claims.put("email", s.getEmail());
            claims.put("name",s.getName());
        }
        if (userDetails instanceof Teacher){
            Teacher t = (Teacher)userDetails;
            claims.put("email", t.getEmail());
            claims.put("name",t.getName());
        }

        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 30))
                .claims(claims)
                .signWith(key,SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims extractAllClaims(String token){
        return Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
    }

    public String extractUsername(String token) {
        return (String)extractAllClaims(token).get("name");
    }

    public String extractEmail(String token){
        return (String)extractAllClaims(token).get("email");
    }

    public boolean validateToken(String username, UserDetails userDetails, String token){
        return username.equals(userDetails.getUsername()) && !isExpired(token);
    }

    private boolean isExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

}
