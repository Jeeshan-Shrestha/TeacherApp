package com.TeacherApp.TeacherApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TeacherApp.TeacherApp.Models.BackendResponse;
import com.TeacherApp.TeacherApp.Models.Notice;
import com.TeacherApp.TeacherApp.Services.NoticeService;



@RestController
@RequestMapping("/api/v1/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @PostMapping("/add")
    public ResponseEntity<BackendResponse> addNotice(@RequestBody Notice notice) {
        String message = noticeService.addNotice(notice);
        return ResponseEntity.ok().body(new BackendResponse(true,message));
    }

    @GetMapping("/my-notice")
    public ResponseEntity<BackendResponse> getNotice() {
        List<Notice> message = noticeService.getNotice();
        return ResponseEntity.ok().body(new BackendResponse(true,message));
    }
    
    

}
