package com.example.dklearn.admin.staff.repository;


import com.example.dklearn.admin.staff.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Long> {

    Staff findByEmail(String email);
    Staff findByUserName(String userName);
}
