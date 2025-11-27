package com.TeacherApp.TeacherApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TeacherApp.TeacherApp.Models.BackendResponse;
import com.TeacherApp.TeacherApp.Models.LoginDTO;
import com.TeacherApp.TeacherApp.Models.Student;
import com.TeacherApp.TeacherApp.Models.Teacher;
import com.TeacherApp.TeacherApp.Services.AuthService;

import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    
    @PostMapping("/student/register")
    public ResponseEntity<BackendResponse> registerStudent(@RequestBody Student student) {
        String message = authService.registerStudent(student);
        return ResponseEntity.ok().body(new BackendResponse(true,message));
    }

    @PostMapping("/teacher/register")
    public ResponseEntity<BackendResponse> registerTeacher(@RequestBody Teacher teacher) {
        String message = authService.registerTeacher(teacher);
        return ResponseEntity.ok().body(new BackendResponse(true,message));
    }

    @PostMapping("/student/login")
    public ResponseEntity<BackendResponse> loginStudent(@RequestBody LoginDTO login, HttpServletResponse response) {
        String message = authService.login(login,response);
        return ResponseEntity.ok().body(new BackendResponse(true,message));
    }
    
    

}
