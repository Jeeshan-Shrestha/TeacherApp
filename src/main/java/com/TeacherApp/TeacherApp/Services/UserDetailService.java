package com.TeacherApp.TeacherApp.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.TeacherApp.TeacherApp.Exceptions.JwtInternalError;
import com.TeacherApp.TeacherApp.Models.Faculty;
import com.TeacherApp.TeacherApp.Models.Student;
import com.TeacherApp.TeacherApp.Models.StudentDTO;
import com.TeacherApp.TeacherApp.Models.Teacher;
import com.TeacherApp.TeacherApp.Models.TeacherDTO;
import com.TeacherApp.TeacherApp.Repositories.StudentRepo;
import com.TeacherApp.TeacherApp.Repositories.TeacherRepo;

@Service
public class UserDetailService {
    
    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private TeacherRepo teacherRepo;

    @Autowired
    private ConversionOfDTOService converter;

    public List<StudentDTO> getAllStudents(){
        return studentRepo.findAll().stream().map(converter::studentToStudentDTO).toList();
    }

    public List<TeacherDTO> getAllTeacher(){
        return teacherRepo.findAll().stream().map(converter::teacherToTeacherDTO).toList();
    }

    public List<StudentDTO> getAllStudentBySection(Student student){
        Faculty faculty = student.getFaculty();
        int semester = student.getSemester();
        String section = student.getSection();
        List<Student> students = studentRepo.findByFacultyAndSemesterAndSection(faculty, semester, section);
        return students.stream().map(converter::studentToStudentDTO).toList();
    }

    public List<StudentDTO> getAllStudentByFaculty(Student student){
        Faculty faculty = student.getFaculty();
        int semester = student.getSemester();
        List<Student> students = studentRepo.findByFacultyAndSemester(faculty, semester);
        return students.stream().map(converter::studentToStudentDTO).toList();
    }

    public UserDetails getMySelf(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof Student s){
            Student student = studentRepo.findByEmail(s.getEmail());
            return student;
        }
        if (principal instanceof Teacher t){
            Teacher teacher = teacherRepo.findByEmail(t.getEmail());
            return teacher;
        }
        throw new JwtInternalError("Jwt internal error");
    }

}
