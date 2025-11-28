package com.TeacherApp.TeacherApp.Models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection="TeacherSubject")
public class TeacherSubject {
    @Id
    private ObjectId id;

    private Faculty faculty;

    private int semester;

    private Section section; 

    private String subjectCode;

    private String subjectName;

}
