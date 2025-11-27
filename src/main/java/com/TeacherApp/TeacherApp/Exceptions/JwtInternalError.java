package com.TeacherApp.TeacherApp.Exceptions;

public class JwtInternalError extends RuntimeException{
    
    public JwtInternalError(String message){
        super(message);
    }

}
