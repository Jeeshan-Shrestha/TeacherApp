package com.TeacherApp.TeacherApp.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TeacherApp.TeacherApp.Exceptions.SubjectAlreadyExistsException;
import com.TeacherApp.TeacherApp.Models.Subject;
import com.TeacherApp.TeacherApp.Repositories.SubjectRepo;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepo subjectRepo;

    public String addSubject(Subject subject){
        Subject subjectOnDb = subjectRepo.findBySubjectCodeAndFaculty(subject.getSubjectCode(), subject.getFaculty());
        if (subjectOnDb != null){
            throw new SubjectAlreadyExistsException("Subject already exists");
        }
        subjectRepo.save(subject);
        return "Successfully added the subject";
    }

    

}
