package com.example.dklearn.admin.administration.model;


import com.example.dklearn.admin.staff.model.AccountDetails;
import com.example.dklearn.admin.user.model.AbstractEntity;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
public class Admin extends AbstractEntity {

    private String fullName;
    private String email;
    private String phoneNumber;
    private int age;
    private String gender;
    private String certicateUrl;
    private  String category;
    private String passwordhash;
    private String userType;
    private String userName;
    private String password;
    private String confirmPassword;
    private Long id;
  //  @OneToMany(fetch = FetchType.EAGER)
//    @LazyCollection(LazyCollectionOption.FALSE)

   // List<AccountDetails> accountDetails;

}
