package com.example.dklearn.admin.productsales.model;

import com.example.dklearn.admin.user.model.AbstractEntity;
import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class ProductSales extends AbstractEntity {

    private long totalQuantityAvailble;
    private long totalQuantitySold;
    private String productDescription;
    private String productDescriptionCode;
    private String productCode;
    private String productCategoryCode;
}
