package com.example.dklearn.images.model;

import com.example.dklearn.admin.user.model.AbstractEntity;
import lombok.Data;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.Entity;

@Entity
@Data
public class Images extends AbstractEntity {
    private String name;
    private String url;
}
