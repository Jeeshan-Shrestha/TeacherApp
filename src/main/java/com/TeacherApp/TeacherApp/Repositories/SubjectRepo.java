package com.TeacherApp.TeacherApp.Repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.TeacherApp.TeacherApp.Models.Faculty;
import com.TeacherApp.TeacherApp.Models.Subject;



@Repository
public interface SubjectRepo extends MongoRepository<Subject, ObjectId>{
    
    public Subject findBySubjectCodeAndFaculty(String subjectCode, Faculty faculty);

    public List<Subject> findByFacultyAndSemester(Faculty faculty,int semester);

}
