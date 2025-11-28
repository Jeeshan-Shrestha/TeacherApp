package com.TeacherApp.TeacherApp.Repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.TeacherApp.TeacherApp.Models.Faculty;
import com.TeacherApp.TeacherApp.Models.Section;
import com.TeacherApp.TeacherApp.Models.TeacherSubject;

@Repository
public interface TeacherSubjectRepo extends MongoRepository<TeacherSubject, ObjectId>{
    
    public List<TeacherSubject> findByIdIn(List<ObjectId> subjectId);

    public boolean existsByFacultyAndSectionAndSubjectCode(Faculty faculty,Section section,String subjectCode);

    public void deleteByFacultyAndSectionAndSubjectCodeAndSemester(Faculty faculty,Section section,String subjectCode,int semester);

    public TeacherSubject findByFacultyAndSectionAndSubjectCode(Faculty faculty,Section section,String subjectCode);
}
