package com.TeacherApp.TeacherApp.Models;

import org.bson.types.ObjectId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO{

    private String name;

    private String email;

    private Faculty faculty;

    private ObjectId id;

    private String section;

}
