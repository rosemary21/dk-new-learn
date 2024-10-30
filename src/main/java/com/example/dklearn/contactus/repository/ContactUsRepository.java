package com.example.dklearn.contactus.repository;


import com.example.dklearn.contactus.model.ContactUs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactUsRepository extends JpaRepository<ContactUs, Long> {
}
