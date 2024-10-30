package com.example.dklearn.admin.productsales.service;


import com.example.dklearn.admin.productsales.dto.ProductSalesDto;
import com.example.dklearn.admin.productsales.resp.ProductSalesResponse;

public interface Productsalesservice {

    ProductSalesResponse updateProductSales(ProductSalesDto productSalesDto);
}
