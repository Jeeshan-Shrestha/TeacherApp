package com.TeacherApp.TeacherApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TeacherApp.TeacherApp.Exceptions.JwtInternalError;
import com.TeacherApp.TeacherApp.Models.BackendResponse;
import com.TeacherApp.TeacherApp.Models.Student;
import com.TeacherApp.TeacherApp.Models.StudentDTO;
import com.TeacherApp.TeacherApp.Models.Teacher;
import com.TeacherApp.TeacherApp.Models.TeacherDTO;
import com.TeacherApp.TeacherApp.Services.ConversionOfDTOService;
import com.TeacherApp.TeacherApp.Services.UserDetailService;




@RestController
@RequestMapping("/api/v1/details")
public class UserDetailController {

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private ConversionOfDTOService converter;
    
    @GetMapping("/students")
    public ResponseEntity<BackendResponse> getAllStudents() {
        List<StudentDTO> allStudents = userDetailService.getAllStudents();
        return ResponseEntity.ok().body(new BackendResponse(true,allStudents));
    }

    @GetMapping("/teachers")
    public ResponseEntity<BackendResponse> getAllTeacher() {
        List<TeacherDTO> allTeacher = userDetailService.getAllTeacher();
        return ResponseEntity.ok().body(new BackendResponse(true,allTeacher));
    }

    @PostMapping("/section")
    public ResponseEntity<BackendResponse> getAllStudentBySection(@RequestBody Student student) {
        List<StudentDTO> allStudents = userDetailService.getAllStudentBySection(student);
        return ResponseEntity.ok().body(new BackendResponse(true,allStudents));
    }
    
    @PostMapping("/faculty")
    public ResponseEntity<BackendResponse> getAllStudentByFaculty(@RequestBody Student student) {
        List<StudentDTO> allStudents = userDetailService.getAllStudentByFaculty(student);
        return ResponseEntity.ok().body(new BackendResponse(true,allStudents));
    }

    @GetMapping("/me")
    public ResponseEntity<BackendResponse> getMySelf() {
        UserDetails mySelf = userDetailService.getMySelf();
        if (mySelf instanceof Student s){
            StudentDTO dto = converter.studentToStudentDTO(s);
            return ResponseEntity.ok().body(new BackendResponse(true,dto));
        }
        if (mySelf instanceof Teacher t){
            TeacherDTO dto = converter.teacherToTeacherDTO(t);
            return ResponseEntity.ok().body(new BackendResponse(true,dto));
        }
        throw new JwtInternalError("Internal Jwt error");
    }
    

}
