package com.example.dklearn.admin.productsales.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductSalesDto {
    private long totalQuantityAvailble;
    private long totalQuantitySold;
    private String productDescription;
    private String productDescriptionCode;
    private String productCode;
    private String productCategoryCode;
}
