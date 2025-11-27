package com.TeacherApp.TeacherApp.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TeacherApp.TeacherApp.Exceptions.SubjectAlreadyExistsException;
import com.TeacherApp.TeacherApp.Models.FacultySemester;
import com.TeacherApp.TeacherApp.Models.Subject;
import com.TeacherApp.TeacherApp.Models.SubjectDTO;
import com.TeacherApp.TeacherApp.Repositories.SubjectRepo;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepo subjectRepo;
    
    @Autowired
    private ConversionOfDTOService converter;

    public String addSubject(Subject subject){
        Subject subjectOnDb = subjectRepo.findBySubjectCodeAndFaculty(subject.getSubjectCode(), subject.getFaculty());
        if (subjectOnDb != null){
            throw new SubjectAlreadyExistsException("Subject already exists");
        }
        subjectRepo.save(subject);
        return "Successfully added the subject";
    }

    public List<SubjectDTO> findByFaculty(FacultySemester facSem){
        List<Subject> subjects = subjectRepo.findByFacultyAndSemester(facSem.getFaculty(), facSem.getSemester());
        return subjects.stream().map(converter::subjectToSubjectDTO).toList();
    }

    

}
