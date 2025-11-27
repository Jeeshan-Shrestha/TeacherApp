package com.TeacherApp.TeacherApp.Exceptions;

public class TeacherAlreadyExistsException extends RuntimeException{
    
    public TeacherAlreadyExistsException(String message){
        super(message);
    }

}
