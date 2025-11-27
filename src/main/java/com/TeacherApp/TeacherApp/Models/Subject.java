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
@Document(collection="Subject")
public class Subject {

    @Id
    private ObjectId id;

    private String subjectCode;
    
    private String name;

    private int semester;

    private Faculty faculty;

}
