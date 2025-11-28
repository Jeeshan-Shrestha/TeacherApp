package com.TeacherApp.TeacherApp.Repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.TeacherApp.TeacherApp.Models.Notes;

@Repository
public interface NotesRepo extends MongoRepository<Notes, ObjectId>{
    
}
