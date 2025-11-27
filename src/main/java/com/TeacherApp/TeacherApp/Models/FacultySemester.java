package com.TeacherApp.TeacherApp.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FacultySemester {
    
    private Faculty faculty;

    private int semester;

}
