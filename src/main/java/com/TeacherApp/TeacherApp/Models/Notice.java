package com.TeacherApp.TeacherApp.Models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="Notice")
public class Notice {
 
    @Id
    private ObjectId id;

    private String title;

    private String description;

    private int semester;

    private Section section;

    private Faculty faculty;

    private ObjectId subjectId;

}
