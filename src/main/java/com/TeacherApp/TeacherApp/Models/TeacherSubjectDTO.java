package com.TeacherApp.TeacherApp.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherSubjectDTO {
    
    private String subjectName;

    private Section section;

    private int semester;

    private String subjectCode;

    private Faculty faculty;

}
