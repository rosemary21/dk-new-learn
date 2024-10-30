package com.example.dklearn.contactus.model;


import com.example.dklearn.admin.user.model.AbstractEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
@Data
public class ContactUs extends AbstractEntity {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;

    @Lob
    private String message;

}
