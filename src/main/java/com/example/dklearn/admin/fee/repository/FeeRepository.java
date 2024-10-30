package com.example.dklearn.admin.fee.repository;

import com.example.dklearn.admin.fee.model.Fee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeeRepository extends JpaRepository<Fee, Long> {

    Fee findByLocalGovArea(String localGovernmentArea);

    Fee findFirstByLocalGovArea(String localGove);
}
