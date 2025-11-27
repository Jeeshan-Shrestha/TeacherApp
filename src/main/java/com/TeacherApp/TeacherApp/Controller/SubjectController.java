package com.TeacherApp.TeacherApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TeacherApp.TeacherApp.Models.BackendResponse;
import com.TeacherApp.TeacherApp.Models.FacultySemester;
import com.TeacherApp.TeacherApp.Models.Subject;
import com.TeacherApp.TeacherApp.Models.SubjectDTO;
import com.TeacherApp.TeacherApp.Services.SubjectService;


@RestController
@RequestMapping("/api/v1/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;
    
    @PostMapping("/add")
    public ResponseEntity<BackendResponse> addSubject(@RequestBody Subject subject) {
        
        String message = subjectService.addSubject(subject);
        return ResponseEntity.ok().body(new BackendResponse(true,message));
    }
    
    @PostMapping("/show")
    public ResponseEntity<BackendResponse> showSubjectByFaculty(@RequestBody FacultySemester facSem) {
        
        List<SubjectDTO> message = subjectService.findByFaculty(facSem);
        return ResponseEntity.ok().body(new BackendResponse(true,message));
    }
    

}
