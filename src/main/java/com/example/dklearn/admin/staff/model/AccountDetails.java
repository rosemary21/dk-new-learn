package com.example.dklearn.admin.staff.model;

import com.example.dklearn.admin.user.model.AbstractEntity;
import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class AccountDetails extends AbstractEntity {
    private String accountName;
    private String accountNumber;
    private String bank;
    private String currency;
}
