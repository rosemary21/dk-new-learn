//package com.example.dklearn.transaction.model;
//
//
//import com.example.dklearn.admin.productdescription.model.Images;
//import com.example.dklearn.admin.user.model.AbstractEntity;
//import com.example.dklearn.admin.user.model.DekeralutiveUser;
//import lombok.Data;
//
//import javax.persistence.Entity;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Entity
//@Data
//public class Transaction extends AbstractEntity {
//
//    private LocalDateTime transactionDate;
//    private BigDecimal amount;
//    private String descriptionCode;
//    private String originalAmount;
//    private String description;
//    private String  currency;
//    private String productCode;
//    private String productCategoryCode;
//    private String stockId;
//    @ManyToOne
//    private DekeralutiveUser dekeralutiveUser;
//    private Integer productDescriptionNumber;
//    private String transactionStatus;
//    private String imageUrl;
//    private String transactionId;
//
//    @OneToMany
//    private List<Images> imageList;
//
//
//
//}
