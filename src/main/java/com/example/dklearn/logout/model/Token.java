package com.example.dklearn.logout.model;


import com.example.dklearn.admin.user.model.AbstractEntity;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;

@Entity
@Where(clause ="del_Flag='N'")
@Data
public class Token extends AbstractEntity {
    private String tokenValue;
    private String status;
    private String userName;
}
