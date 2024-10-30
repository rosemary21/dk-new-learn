package com.example.dklearn.admin.paymentstructure.repository;


import com.example.dklearn.admin.paymentstructure.model.PaymentStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentStructureRepository extends JpaRepository<PaymentStructure, Long> {

    List<PaymentStructure> findByProductDescriptionCode(String proddescripCode);
}
