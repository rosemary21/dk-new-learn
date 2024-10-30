package com.example.dklearn.admin.productsales.controller;


import com.example.dklearn.admin.productsales.dto.ProductSalesDto;
import com.example.dklearn.admin.productsales.resp.ProductSalesResponse;
import com.example.dklearn.admin.productsales.service.Productsalesservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/dl/api/v1/productsales", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ProductSalesController {


    @Autowired
    Productsalesservice productsalesservice;

    @RequestMapping(value = "/add" ,method = RequestMethod.POST)
    public ResponseEntity<ProductSalesResponse> addProduct(@RequestBody ProductSalesDto productDto) {
        ProductSalesResponse responseDto= productsalesservice.updateProductSales(productDto);
        if(responseDto.getResponseDto().getCode().equalsIgnoreCase("dkss")){
            return new ResponseEntity<ProductSalesResponse>(responseDto, HttpStatus.OK);
        }
        else
            return new ResponseEntity<ProductSalesResponse>(responseDto, HttpStatus.BAD_REQUEST);
    }

}
