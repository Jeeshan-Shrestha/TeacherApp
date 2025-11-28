package com.TeacherApp.TeacherApp.Services;

import org.springframework.stereotype.Service;

import com.TeacherApp.TeacherApp.Models.Student;
import com.TeacherApp.TeacherApp.Models.StudentDTO;
import com.TeacherApp.TeacherApp.Models.Subject;
import com.TeacherApp.TeacherApp.Models.SubjectDTO;
import com.TeacherApp.TeacherApp.Models.Teacher;
import com.TeacherApp.TeacherApp.Models.TeacherDTO;
import com.TeacherApp.TeacherApp.Models.TeacherSubject;
import com.TeacherApp.TeacherApp.Models.TeacherSubjectDTO;

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

    public SubjectDTO subjectToSubjectDTO(Subject subject){
        SubjectDTO dto = new SubjectDTO();
        dto.setName(subject.getName());
        dto.setSubjectCode(subject.getSubjectCode());
        return dto;
    }

    public SubjectDTO teacherSubjectToSubjectDTO(TeacherSubject subject){
        SubjectDTO dto = new SubjectDTO();
        dto.setName(subject.getSubjectName());
        dto.setSubjectCode(subject.getSubjectCode());
        return dto;
    }

    public TeacherSubjectDTO teacherSubjectToTeacherSubjectDTO(TeacherSubject subject){
        TeacherSubjectDTO dto = new TeacherSubjectDTO();
        dto.setFaculty(subject.getFaculty());
        dto.setSection(subject.getSection());
        dto.setSemester(subject.getSemester());
        dto.setSubjectName(subject.getSubjectName());
        dto.setSubjectCode(subject.getSubjectCode());
        return dto;
    }

}
