package com.TeacherApp.TeacherApp.Services;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.TeacherApp.TeacherApp.Exceptions.MongoDBDeletionException;
import com.TeacherApp.TeacherApp.Exceptions.SubjectAlreadyExistsException;
import com.TeacherApp.TeacherApp.Models.Subject;
import com.TeacherApp.TeacherApp.Models.Teacher;
import com.TeacherApp.TeacherApp.Models.TeacherSubject;
import com.TeacherApp.TeacherApp.Models.TeacherSubjectDTO;
import com.TeacherApp.TeacherApp.Repositories.SubjectRepo;
import com.TeacherApp.TeacherApp.Repositories.TeacherRepo;
import com.TeacherApp.TeacherApp.Repositories.TeacherSubjectRepo;

@Service
public class TeacherSubjectService {
    
    @Autowired
    private TeacherSubjectRepo teacherSubjectRepo;

    @Autowired
    private TeacherRepo teacherRepo;

    @Autowired
    private SubjectRepo subjectRepo;

    @Autowired
    private ConversionOfDTOService converter;

    public String addTeacherSubject(TeacherSubject teacherSubject){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(principal instanceof Teacher t)){
           throw new RuntimeException("You need to be either teacher or admin");
        }
        if (teacherSubjectRepo.existsByFacultyAndSectionAndSubjectCode(teacherSubject.getFaculty(), teacherSubject.getSection(), teacherSubject.getSubjectCode())){
            throw new SubjectAlreadyExistsException("This subject already exists in the teacher database");
        }
        Subject subject = subjectRepo.findBySubjectCodeAndFaculty(teacherSubject.getSubjectCode(), teacherSubject.getFaculty());
        if (subject == null){
            throw new RuntimeException("This subject doesnt exists for the given criteria");
        }
        teacherSubject.setSubjectName(subject.getName());
        TeacherSubject teacherSubjectWithId = teacherSubjectRepo.save(teacherSubject);
        Teacher teacher = teacherRepo.findById(t.getId()).orElseThrow(()-> new RuntimeException("Teacher not found"));
        List<ObjectId> teacherSubjectIds = teacher.getTeacherSubjectId();
        teacherSubjectIds.add(teacherSubjectWithId.getId());
        teacher.setTeacherSubjectId(teacherSubjectIds);
        teacherRepo.save(teacher);

        return "Successfully saved";
    }

    public List<TeacherSubjectDTO> getCurrentTeacherSubject(){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(principal instanceof Teacher t)){
            throw new RuntimeException("You need to be teacher or admin");
        }
        List<ObjectId> subjectIds = t.getTeacherSubjectId();
        List<TeacherSubject> teacherSubjects = teacherSubjectRepo.findByIdIn(subjectIds);
        List<TeacherSubjectDTO> subjectDTOs = new ArrayList<>();
        for (TeacherSubject teacherSubject : teacherSubjects){
            TeacherSubjectDTO dto = converter.teacherSubjectToTeacherSubjectDTO(teacherSubject);
            subjectDTOs.add(dto);
        }
        return subjectDTOs;    
    }

    public String deleteTeacherSubject(TeacherSubjectDTO subject){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(principal instanceof Teacher t)){
            throw new RuntimeException("You need teacher role to perform this action");
        }

        if (teacherSubjectRepo.existsByFacultyAndSectionAndSubjectCode(subject.getFaculty(), subject.getSection(), subject.getSubjectCode())){
           
            TeacherSubject subjectOnDB = teacherSubjectRepo.findByFacultyAndSectionAndSubjectCode(subject.getFaculty(), subject.getSection(), subject.getSubjectCode());
            ObjectId id = subjectOnDB.getId();
            t.getTeacherSubjectId().remove(id);
            teacherRepo.save(t);
            teacherSubjectRepo.deleteByFacultyAndSectionAndSubjectCodeAndSemester(subject.getFaculty(), subject.getSection(), subject.getSubjectCode(), subject.getSemester());
            return "Successfully deleted";
        }
        throw new MongoDBDeletionException("No such subject exists in the teacher's collection");
    }

}
