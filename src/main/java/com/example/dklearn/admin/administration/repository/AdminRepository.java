package com.example.dklearn.admin.administration.repository;


import com.example.dklearn.admin.administration.model.Admin;
import com.example.dklearn.admin.staff.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin findByEmail(String email);
    Admin findByUserName(String userName);
}
