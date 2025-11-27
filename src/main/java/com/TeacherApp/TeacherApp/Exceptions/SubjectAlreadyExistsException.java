package com.TeacherApp.TeacherApp.Exceptions;

public class SubjectAlreadyExistsException extends RuntimeException{
    
    public SubjectAlreadyExistsException(String message){
        super(message);
    }

}
