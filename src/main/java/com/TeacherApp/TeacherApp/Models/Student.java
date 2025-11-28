package com.TeacherApp.TeacherApp.Models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection="Student")
public class Student implements UserDetails{
    
    @Id
    private ObjectId id;

    private String name;

    @Indexed(unique=true)
    private String email;

    private String password;

    private int semester;

    private String section;

    private Faculty faculty;

    private String role;

    private List<ObjectId> notesId = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public String getUsername() {
        return email;
    }
    
}
