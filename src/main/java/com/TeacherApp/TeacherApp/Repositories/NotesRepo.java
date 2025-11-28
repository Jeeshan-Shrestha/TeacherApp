package com.TeacherApp.TeacherApp.Repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.TeacherApp.TeacherApp.Models.Faculty;
import com.TeacherApp.TeacherApp.Models.Notes;
import com.TeacherApp.TeacherApp.Models.Section;

@Repository
public interface NotesRepo extends MongoRepository<Notes, ObjectId>{
    
    public List<Notes> findByFacultyAndSemesterAndSection(Faculty faculty,int semester,Section section);

}
