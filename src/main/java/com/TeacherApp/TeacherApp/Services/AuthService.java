package com.TeacherApp.TeacherApp.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.TeacherApp.TeacherApp.Models.LoginDTO;
import com.TeacherApp.TeacherApp.Models.Student;
import com.TeacherApp.TeacherApp.Models.Teacher;
import com.TeacherApp.TeacherApp.Repositories.StudentRepo;
import com.TeacherApp.TeacherApp.Repositories.TeacherRepo;

@Service
public class AuthService {
    
    @Autowired
    private StudentRepo studentRepo;

    @Autowired 
    private TeacherRepo teacherRepo;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public String registerStudent(Student student){
        student.setPassword(encoder.encode(student.getPassword()));
        studentRepo.save(student);
        return "Succesfully registered";
    }

    public String registerTeacher(Teacher teacher){
        teacher.setPassword(encoder.encode(teacher.getPassword()));
        teacherRepo.save(teacher);
        return "successfully registered";
    }

    public String loginStudent(LoginDTO loginDTO){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
        if (authentication.isAuthenticated()){

        }
    }

}
