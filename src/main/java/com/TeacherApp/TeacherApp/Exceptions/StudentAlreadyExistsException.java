package com.TeacherApp.TeacherApp.Exceptions;

public class StudentAlreadyExistsException extends RuntimeException{
    
    public StudentAlreadyExistsException(String message){
        super(message);
    }

}
