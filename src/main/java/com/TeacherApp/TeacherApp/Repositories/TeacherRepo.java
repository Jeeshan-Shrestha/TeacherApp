package com.TeacherApp.TeacherApp.Repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.TeacherApp.TeacherApp.Models.Teacher;

@Repository
public interface TeacherRepo extends MongoRepository<Teacher, ObjectId>{
    
    public Teacher findByUsername(String username);

}
