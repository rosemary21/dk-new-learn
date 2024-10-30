package com.example.dklearn.transaction.repository;



import com.example.dklearn.admin.user.model.DekeralutiveUser;
import com.example.dklearn.customertransaction.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository  extends JpaRepository<Transaction, Long> {

    List<Transaction> findByCourseId(String courseId);

    Page<Transaction> findByCourseId(String courseId,Pageable pageable);

}
