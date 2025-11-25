package com.TeacherApp.TeacherApp.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BackendResponse {
    
    private boolean success;
    private Object message;

}
