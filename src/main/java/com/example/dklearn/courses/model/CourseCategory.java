package com.example.dklearn.courses.model;

import com.example.dklearn.admin.user.model.AbstractEntity;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class CourseCategory  extends AbstractEntity {

    private String code;
    private String description;
}
