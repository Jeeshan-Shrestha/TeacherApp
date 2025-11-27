package com.TeacherApp.TeacherApp.Services;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.TeacherApp.TeacherApp.Models.Teacher;
import com.TeacherApp.TeacherApp.Models.TeacherSubject;
import com.TeacherApp.TeacherApp.Repositories.TeacherRepo;
import com.TeacherApp.TeacherApp.Repositories.TeacherSubjectRepo;

@Service
public class TeacherSubjectService {
    
    @Autowired
    private TeacherSubjectRepo teacherSubjectRepo;

    @Autowired
    private TeacherRepo teacherRepo;

    public String addTeacherSubject(TeacherSubject teacherSubject){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(principal instanceof Teacher t)){
           throw new RuntimeException("You need to be either teacher or admin");
        }
        Teacher teacher = teacherRepo.findById(t.getId()).orElseThrow(()-> new RuntimeException("Teacher not found"));

        TeacherSubject teacherSubjectWithId = teacherSubjectRepo.save(teacherSubject);

        List<ObjectId> teacherSubjectIds = teacher.getTeacherSubjectId();
        teacherSubjectIds.add(teacherSubjectWithId.getId());
        teacher.setTeacherSubjectId(teacherSubjectIds);
        teacherRepo.save(teacher);

        return "Successfully saved";
    }
}
