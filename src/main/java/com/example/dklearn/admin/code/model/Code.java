package com.example.dklearn.admin.code.model;


import com.example.dklearn.admin.user.model.AbstractEntity;
import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Code extends AbstractEntity {

    private String code;
    private String description;
}
