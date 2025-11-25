package com.TeacherApp.TeacherApp.Jwt;

import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.TeacherApp.TeacherApp.Models.Student;
import com.TeacherApp.TeacherApp.Models.Teacher;

import io.jsonwebtoken.security.Keys;

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
    }

}
