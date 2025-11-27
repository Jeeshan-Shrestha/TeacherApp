package com.TeacherApp.TeacherApp.Exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.TeacherApp.TeacherApp.Models.BackendResponse;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<BackendResponse> usernameNotFoundExceptionHandler(RuntimeException e){
        return ResponseEntity.badRequest().body(new BackendResponse(false,e.getMessage()));
    }
}
