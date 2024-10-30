package com.example.dklearn.courses.model;

import com.example.dklearn.admin.user.model.AbstractEntity;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class CourseGroup  extends AbstractEntity {

    private String code;
    private String description;

}
