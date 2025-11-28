package com.TeacherApp.TeacherApp.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.TeacherApp.TeacherApp.Models.Notice;
import com.TeacherApp.TeacherApp.Models.NoticeDTO;
import com.TeacherApp.TeacherApp.Models.Student;
import com.TeacherApp.TeacherApp.Repositories.NoticeRepo;

@Service
public class NoticeService {
    
    @Autowired
    private NoticeRepo noticeRepo;

    @Autowired
    private ConversionOfDTOService converter;

    public String addNotice(Notice notice){
        noticeRepo.save(notice);
        return "successfully added the notice";
    }

    public List<NoticeDTO> getNotice(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof Student s){
            List<Notice> notices = noticeRepo.findByFacultyAndSemesterAndSection(s.getFaculty(), s.getSemester(), s.getSection());
            return notices.stream().map(converter::noticeToNoticeDTO).toList();
        }
        throw new RuntimeException("you must be student to see the notices");
    }

}
