package com.TeacherApp.TeacherApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TeacherApp.TeacherApp.Models.BackendResponse;
import com.TeacherApp.TeacherApp.Models.TeacherSubject;
import com.TeacherApp.TeacherApp.Services.TeacherSubjectService;


@RestController
@RequestMapping("/api/v1/teacher")
public class TeacherSubjectController {

    @Autowired
    private TeacherSubjectService teacherSubjectService;
    
    @PostMapping("/assign")
    public ResponseEntity<BackendResponse> addTeacherSubject(@RequestBody TeacherSubject teacherSubject) {
        String message = teacherSubjectService.addTeacherSubject(teacherSubject);
        return ResponseEntity.ok().body(new BackendResponse(true,message));
    }
    

}
