package com.TeacherApp.TeacherApp.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.TeacherApp.TeacherApp.Models.Student;
import com.TeacherApp.TeacherApp.Models.Teacher;
import com.TeacherApp.TeacherApp.Repositories.StudentRepo;
import com.TeacherApp.TeacherApp.Repositories.TeacherRepo;

@Service
public class MyUserDetailsService implements UserDetailsService{

    @Autowired
    StudentRepo studentRepo;

    @Autowired
    TeacherRepo teacherRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        Student student = studentRepo.findByEmail(email);
        Teacher teacher = teacherRepo.findByEmail(email);

        if (student != null){
            return student;
        }

        if (teacher != null){
            return teacher;
        }

        throw new UsernameNotFoundException("User not found");
    }

    
    
}
