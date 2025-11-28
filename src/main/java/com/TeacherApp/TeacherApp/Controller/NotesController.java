package com.TeacherApp.TeacherApp.Controller;

import java.io.IOException;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.TeacherApp.TeacherApp.Models.BackendResponse;
import com.TeacherApp.TeacherApp.Models.Faculty;
import com.TeacherApp.TeacherApp.Models.Notes;
import com.TeacherApp.TeacherApp.Models.Section;
import com.TeacherApp.TeacherApp.Services.NotesService;


@RestController
@RequestMapping("/api/v1/note")
public class NotesController {

    @Autowired
    private NotesService notesService;
    
    @PostMapping("/add")
    public ResponseEntity<BackendResponse> addNote(
        @RequestParam String title,
        @RequestParam Faculty faculty,
        @RequestParam int semester,
        @RequestParam Section section,
        @RequestParam ObjectId subjectId,
        @RequestParam MultipartFile file
    ) throws IOException {
        Notes note = new Notes();
        note.setTitle(title);
        note.setFaculty(faculty);
        note.setSemester(semester);
        note.setSection(section);
        note.setSubjectId(subjectId);
        note.setFileContent(file.getBytes());
        note.setFileName(file.getOriginalFilename());
        note.setContentType(file.getContentType());
        String message = notesService.addNote(note);
        return ResponseEntity.ok().body(new BackendResponse(true,message));
    }
    

}
