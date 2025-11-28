package com.TeacherApp.TeacherApp.Repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.TeacherApp.TeacherApp.Models.Faculty;
import com.TeacherApp.TeacherApp.Models.Section;
import com.TeacherApp.TeacherApp.Models.Student;

@Repository
public interface StudentRepo extends MongoRepository<Student, ObjectId>{
    
    public Student findByEmail(String email);

    public List<Student> findByFacultyAndSemesterAndSection(Faculty faculty,int semester,Section section);

    public List<Student> findByFacultyAndSemester(Faculty faculty,int semester);

}
