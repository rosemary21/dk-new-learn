package com.example.dklearn.admin.user.repository;


import com.example.dklearn.admin.user.model.DekeralutiveUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<DekeralutiveUser, Long> {

    DekeralutiveUser findByUserName(String userName);

    DekeralutiveUser findByEmail(String emailAddress);
}
