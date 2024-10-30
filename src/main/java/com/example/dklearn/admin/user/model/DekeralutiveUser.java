package com.example.dklearn.admin.user.model;

import com.example.dklearn.courses.model.Courses;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class DekeralutiveUser extends AbstractEntity{

    private String firstName;
    private String lastName;
    private String email;
    private String passwordhash;
    private String userType;
    private String phoneNumber;
    private String userName;
    private String fullName;

    @ManyToMany(fetch = FetchType.EAGER)
    List<Courses> coursesList;

}
