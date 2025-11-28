package com.TeacherApp.TeacherApp.Services;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.TeacherApp.TeacherApp.Models.Notes;
import com.TeacherApp.TeacherApp.Models.NotesDTO;
import com.TeacherApp.TeacherApp.Models.Student;
import com.TeacherApp.TeacherApp.Models.Teacher;
import com.TeacherApp.TeacherApp.Repositories.NotesRepo;
import com.TeacherApp.TeacherApp.Repositories.StudentRepo;
import com.TeacherApp.TeacherApp.Repositories.TeacherRepo;

@Service
public class NotesService {
    
    @Autowired
    private NotesRepo notesRepo;

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private TeacherRepo teacherRepo;

    @Autowired
    private ConversionOfDTOService converter;

    public String addNote(Notes note){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof Student s){
            Notes noteWithId = notesRepo.save(note);
            ObjectId id = noteWithId.getId();
            s.getNotesId().add(id);
            studentRepo.save(s);
            return "Note Added";
        }

        if (principal instanceof Teacher t){
            Notes noteWithId = notesRepo.save(note);
            ObjectId id = noteWithId.getId();
            t.getNotesId().add(id);
            teacherRepo.save(t);
            return "Note added";
        }

        throw new RuntimeException("Couldn't add the note");
    }

    public List<NotesDTO> getMyNotes(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof Student s){
            List<Notes> notes = notesRepo.findByFacultyAndSemesterAndSection(s.getFaculty(), s.getSemester(), s.getSection());
            return notes.stream().map(converter::notesToNotesDTO).toList();
        }

        if (principal instanceof Teacher t){
            List<ObjectId> notesId = t.getNotesId();
            List<Notes> notes = notesRepo.findAllById(notesId);
            return notes.stream().map(converter::notesToNotesDTO).toList();
        }
        throw new RuntimeException("Couldnt find the notes");
    }

}
