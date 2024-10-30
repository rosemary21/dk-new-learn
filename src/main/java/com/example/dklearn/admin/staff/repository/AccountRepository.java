package com.example.dklearn.admin.staff.repository;

import com.example.dklearn.admin.staff.model.AccountDetails;
import com.example.dklearn.admin.staff.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountDetails, Long> {
}
