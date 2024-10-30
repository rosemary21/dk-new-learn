package com.example.dklearn.admin.fee.model;


import com.example.dklearn.admin.user.model.AbstractEntity;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Fee extends AbstractEntity {

    private String localGovArea;
    private String doorDeliveryFee;
    private String deliveryPeriod;

}
