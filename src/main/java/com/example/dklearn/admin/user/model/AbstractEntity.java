package com.example.dklearn.admin.user.model;


import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Data
@MappedSuperclass
public class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Version
    protected int version;

    protected String delFlag = "N";

//    @NotNull
    private Date createdOn;

    private Date modifiedOn;


//    @PrePersist
//    void onCreate(){
//        if(createdOn == null){
//            createdOn = new Date();
//        }
//    }
}
