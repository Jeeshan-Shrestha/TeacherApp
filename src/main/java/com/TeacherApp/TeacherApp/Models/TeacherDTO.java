package com.TeacherApp.TeacherApp.Models;

import org.bson.types.ObjectId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDTO{
    
    private ObjectId id;

    private String name;

    private String email;

}
