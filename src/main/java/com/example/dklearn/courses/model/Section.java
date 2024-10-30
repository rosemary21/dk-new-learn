package com.example.dklearn.courses.model;

import com.example.dklearn.admin.user.model.AbstractEntity;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Lob;

@Data
@Entity
public class Section extends AbstractEntity {

    private String title;

    @Type( type = "org.hibernate.type.TextType")
    @Lob
    private String series;


}
