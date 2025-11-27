package com.TeacherApp.TeacherApp.Services;

import org.springframework.stereotype.Service;

import com.TeacherApp.TeacherApp.Models.Student;
import com.TeacherApp.TeacherApp.Models.StudentDTO;
import com.TeacherApp.TeacherApp.Models.Teacher;
import com.TeacherApp.TeacherApp.Models.TeacherDTO;

@Service
public class ConversionOfDTOService {
    

    public StudentDTO studentToStudentDTO(Student student){
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setEmail(student.getEmail());
        dto.setFaculty(student.getFaculty());
        dto.setSection(student.getSection());
        dto.setName(student.getName());
        return dto;
    }

    public TeacherDTO teacherToTeacherDTO(Teacher teacher){
        TeacherDTO dto = new TeacherDTO();
        dto.setEmail(teacher.getEmail());
        dto.setId(teacher.getId());
        dto.setName(teacher.getName());
        return dto;
    }

}
