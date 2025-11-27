package com.TeacherApp.TeacherApp.Services;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.TeacherApp.TeacherApp.Exceptions.StudentAlreadyExistsException;
import com.TeacherApp.TeacherApp.Exceptions.TeacherAlreadyExistsException;
import com.TeacherApp.TeacherApp.Jwt.JwtUtils;
import com.TeacherApp.TeacherApp.Models.LoginDTO;
import com.TeacherApp.TeacherApp.Models.Student;
import com.TeacherApp.TeacherApp.Models.Teacher;
import com.TeacherApp.TeacherApp.Repositories.StudentRepo;
import com.TeacherApp.TeacherApp.Repositories.TeacherRepo;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class AuthService {
    
    @Autowired
    private StudentRepo studentRepo;

    @Autowired 
    private TeacherRepo teacherRepo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    public String registerStudent(Student student){
        if (studentRepo.findByEmail(student.getEmail())!= null){
            throw new StudentAlreadyExistsException("This student already exists");
        }
        student.setPassword(encoder.encode(student.getPassword()));
        student.setRole("ROLE_STUDENT");
        studentRepo.save(student);
        return "Succesfully registered";
    }

    public String registerTeacher(Teacher teacher){
        if (teacherRepo.findByEmail(teacher.getEmail())!= null){
            throw new TeacherAlreadyExistsException("This teacher already exists");
        }
        teacher.setPassword(encoder.encode(teacher.getPassword()));
        teacher.setRole("ROLE_TEACHER");
        teacherRepo.save(teacher);
        return "successfully registered";
    }

    public String login(LoginDTO loginDTO, HttpServletResponse response){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
        if (authentication.isAuthenticated()){
            UserDetails userDetails = myUserDetailsService.loadUserByUsername(loginDTO.getEmail());
            String token = jwtUtils.generateToken(userDetails);
            ResponseCookie cookie = ResponseCookie.from("token",token)
                                                    .httpOnly(true)
                                                    .secure(true)
                                                    .path("/")
                                                    .maxAge(Duration.ofDays(30))
                                                    .sameSite("None")
                                                    .build();
            response.addHeader("Set-Cookie", cookie.toString());
            return token;
        }
        throw new BadCredentialsException("Wrong credentials");
    }

}
